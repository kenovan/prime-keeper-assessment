package com.prime.keeper.assessment.service.transaction;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.keeper.assessment.exception.transaction.AccountInsufficientFundException;
import com.prime.keeper.assessment.exception.transaction.InvalidAccountIdException;
import com.prime.keeper.assessment.exception.transaction.MerchantToMerchantTransferException;
import com.prime.keeper.assessment.exception.transaction.PerTransactionLimitException;
import com.prime.keeper.assessment.exception.transaction.SelfAccountTransferException;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.account.AppUserAccount;
import com.prime.keeper.assessment.model.account.AppUserAccountDetail;
import com.prime.keeper.assessment.model.account.TransactionType;
import com.prime.keeper.assessment.model.join.AppUserRole;
import com.prime.keeper.assessment.model.login.AppUserLogin;
import com.prime.keeper.assessment.model.properties.AppProperties;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.persistence.account.AppUserAccountRepository;
import com.prime.keeper.assessment.persistence.account.detail.AppUserAccountDetailRepository;
import com.prime.keeper.assessment.persistence.login.AppUserLoginRepository;
import com.prime.keeper.assessment.persistence.properties.AppPropertiesRepository;
import com.prime.keeper.assessment.persistence.user.AppUserRepository;
import com.prime.keeper.assessment.utils.RequestUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private RequestUtil requestUtil;
	
	@Autowired
	private AppPropertiesRepository appPropertiesRepository;
	
	@Autowired
	private AppUserLoginRepository appUserLoginRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppUserAccountRepository appUserAccountRepository;
	
	@Autowired
	private AppUserAccountDetailRepository appUserAccountDetailRepository;
	
	/* (non-Javadoc)
	 * @see com.prime.keeper.assessment.service.transaction.TransactionService#transfer(java.lang.String, int)
	 */
	@Override
	@Transactional(value = TxType.REQUIRED)
	public AppUserAccount transfer(String userName, int amount) throws PerTransactionLimitException,
			SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException {
		AppUser appUser = appUserRepository.findOneByUserName(userName);
		return this.transfer(appUser, amount);
	}
	
	/* (non-Javadoc)
	 * @see com.prime.keeper.assessment.service.transaction.TransactionService#transfer(int, int)
	 */
	@Override
	@Transactional(value = TxType.REQUIRED)
	public AppUserAccount transfer(int accountId, int amount) throws InvalidAccountIdException, UserNotFoundException, PerTransactionLimitException, SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException {
		Optional<AppUserAccount> appUserAccountOptional = appUserAccountRepository.findById(accountId);
		if(appUserAccountOptional.isPresent() == false) {
			throw new InvalidAccountIdException(String.format("Invalid account ID: %d", accountId));
		}
		AppUserAccount appUserAccount = appUserAccountOptional.get();
		Optional<AppUser> appUserOptional = appUserRepository.findById(appUserAccount.getUserId());
		if(appUserOptional.isPresent() == false) {
			throw new UserNotFoundException(String.format("Unable to find user by id: %d", appUserAccount.getUserId()));
		}
		return this.transfer(appUserOptional.get(), amount);
	}
	
	private AppUserAccount transfer(AppUser receipientUser , int amount) throws MerchantToMerchantTransferException, PerTransactionLimitException, SelfAccountTransferException, AccountInsufficientFundException {
		AppUserLogin appUserLogin = appUserLoginRepository.findOneByUserSessionAndUserToken(requestUtil.getSessionId(), requestUtil.getAuthToken());
		AppUser senderUser = appUserLogin.getAppUser();
		this.validate(senderUser, receipientUser, amount);
		AppUserAccount senderAccount = this.credit(senderUser, receipientUser, amount);
		this.debit(senderUser, receipientUser, amount);
		return senderAccount;
	}
	
	private void validate(AppUser sender, AppUser receipient, int amount) throws MerchantToMerchantTransferException, PerTransactionLimitException, SelfAccountTransferException, AccountInsufficientFundException {
		AppProperties totalAmountLimitPerTransactionProperties = appPropertiesRepository.findByAppKey("total.amount.limit.per.transaction");
		AppProperties allowMerchantsTransferToMerchants = appPropertiesRepository.findByAppKey("allow.merchant.transfer.to.merchant");
		
		if(sender.getId() == receipient.getId()) {
			throw new SelfAccountTransferException("Unable to send and receive in the same account at a same time.");
		}
		
		AppUserAccount senderAccount = sender.getAppUserAccounts().get(0);
		if(senderAccount.getBalanceAmount() < amount) {
			throw new AccountInsufficientFundException(String.format("Your Account left: %d, not enough to tranfer this amount: %d", senderAccount.getBalanceAmount(), amount));
		}
		
		int amountLimitPerTransaction = Integer.MAX_VALUE;
		if(totalAmountLimitPerTransactionProperties != null) {
			if(NumberUtils.isCreatable(totalAmountLimitPerTransactionProperties.getAppValue())) {
				amountLimitPerTransaction = NumberUtils.toInt(totalAmountLimitPerTransactionProperties.getAppValue());
			}
		}
		
		if (amount > amountLimitPerTransaction) {
			throw new PerTransactionLimitException(String.format("Only allow maximum %s per transaction", amountLimitPerTransaction));
		}
		
		if(this.isUserMerchant(sender)) {
			boolean isAllowMerchantToMerchantTransfer = BooleanUtils.toBoolean(StringUtils.defaultString(allowMerchantsTransferToMerchants.getAppValue(), "true"));
			if(isAllowMerchantToMerchantTransfer == false) {
				if(this.isUserMerchant(receipient)) {
					throw new MerchantToMerchantTransferException("Not allow merchant transfer to another merchant.");
				}
			}
		}
		
	}
	
	private AppUserAccount credit(AppUser sender, AppUser receipient, int amount) {
		AppUserAccount senderAccount = sender.getAppUserAccounts().get(0);
		AppUserAccount receipientAccount = receipient.getAppUserAccounts().get(0);
		AppUserAccountDetail senderAccountDetail = this.createAccountDetail(senderAccount.getId(), new Date(), amount, TransactionType.CREDIT.getValue(), String.format("Transfer %d to reciepient account id: %s", amount, receipientAccount.getId()));
		appUserAccountDetailRepository.save(senderAccountDetail);
		senderAccount.setBalanceAmount(senderAccount.getBalanceAmount() - amount);
		appUserAccountRepository.save(senderAccount);
		return senderAccount;
	}
	
	private void debit(AppUser sender, AppUser receipient, int amount) {
		AppUserAccount senderAccount = sender.getAppUserAccounts().get(0);
		AppUserAccount receipientAccount = receipient.getAppUserAccounts().get(0);
		AppUserAccountDetail receipientAccountDetail = this.createAccountDetail(senderAccount.getId(), new Date(), amount, TransactionType.DEBIT.getValue(), String.format("Receive %d amount from sender account id: %s", amount, senderAccount.getId()));
		appUserAccountDetailRepository.save(receipientAccountDetail);
		receipientAccount.setBalanceAmount(receipientAccount.getBalanceAmount() + amount);
		appUserAccountRepository.save(receipientAccount);
	}
	
	private AppUserAccountDetail createAccountDetail(int accountId, Date now, int amount, String transactionType, String remarks) {
		AppUserAccountDetail appUserAccountDetail = new AppUserAccountDetail();
		appUserAccountDetail.setAccountId(accountId);
		appUserAccountDetail.setCreateDate(now);
		appUserAccountDetail.setRemarks(remarks);
		appUserAccountDetail.setTransactionAmount(amount);
		appUserAccountDetail.setTransactionType(transactionType);
		return appUserAccountDetail;
	}
	
	private boolean isUserMerchant(AppUser user) {
		boolean isMerchant = false;
		for(AppUserRole appUserRole : user.getAppUserRoles()) {
			if("merchant".equals(appUserRole.getAppRole().getRoleName())) {
				isMerchant = true;
			}
		}
		return isMerchant;
	}
	
}
