package com.prime.keeper.assessment.service.user;

import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.keeper.assessment.exception.common.MissingParameterException;
import com.prime.keeper.assessment.exception.user.DuplicateUserNameException;
import com.prime.keeper.assessment.exception.user.InvalidUserRoleException;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.account.AppUserAccount;
import com.prime.keeper.assessment.model.account.AppUserAccountDetail;
import com.prime.keeper.assessment.model.account.TransactionType;
import com.prime.keeper.assessment.model.join.AppUserRole;
import com.prime.keeper.assessment.model.properties.AppProperties;
import com.prime.keeper.assessment.model.role.AppRole;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.persistence.account.AppUserAccountRepository;
import com.prime.keeper.assessment.persistence.account.detail.AppUserAccountDetailRepository;
import com.prime.keeper.assessment.persistence.properties.AppPropertiesRepository;
import com.prime.keeper.assessment.persistence.role.AppRoleRepository;
import com.prime.keeper.assessment.persistence.user.AppUserRepository;
import com.prime.keeper.assessment.persistence.user.role.AppUserRoleRepository;
import com.prime.keeper.assessment.utils.EncryptPasswordUtil;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private static final Logger log = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);
	
	@Autowired
	private AppPropertiesRepository appPropertiesRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private AppUserRoleRepository appUserRoleRepository;
	
	@Autowired
	private AppUserAccountRepository appUserAccountRepository;
	
	@Autowired
	private AppUserAccountDetailRepository appUserAccountDetailRepository;
	
	/* (non-Javadoc)
	 * @see com.prime.keeper.assessment.service.user.UserRegistrationService#registerUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, dontRollbackOn = {MissingParameterException.class, InvalidUserRoleException.class, DuplicateUserNameException.class}, rollbackOn = { Exception.class })
	public AppUser registerUser(String userName, String password, String roleName) throws MissingParameterException, InvalidUserRoleException, DuplicateUserNameException, Exception {
		AppUser appUser = new AppUser();
		try {
			if(StringUtils.isBlank(roleName)) {
				throw new MissingParameterException("Role Name is empty.");
			}
			AppRole appRole = appRoleRepository.findOneByRoleName(roleName);
			if(appRole == null) {
				throw new InvalidUserRoleException(String.format("Invalid Role Name : %s", roleName));
			}
			
			if(StringUtils.isBlank(userName)) {
				throw new MissingParameterException("User Name is empty.");
			}
			
			Integer userInRepoByUserNameCount = appUserRepository.countByUserName(userName);
			if(userInRepoByUserNameCount.intValue() > 0) {
				throw new DuplicateUserNameException(String.format("%s is exists in our system.", userName));
			}
			
			AppUserRole appUserRole = new AppUserRole();
			Date now = new Date();
			
			//create user
			appUser.setUserName(userName);
			appUser.setUserPassword(EncryptPasswordUtil.encrypt(password));
			appUser.setCreateDate(now);
			appUser = appUserRepository.save(appUser);
			
			//create assign role to user
			appUserRole.setUserId(appUser.getId());
			appUserRole.setRoleId(appRole.getId());
			appUserRole = appUserRoleRepository.save(appUserRole);
			
			//get register account information
			int initialAmount = 0;
			AppProperties appProperties = appPropertiesRepository.findByAppKey("new.register.user.gift.amount");
			if(appProperties != null) {
				initialAmount = NumberUtils.toInt(appProperties.getAppValue(), initialAmount);
			}
			
			//create account for user
			AppUserAccount appUserAccount = new AppUserAccount();
			appUserAccount.setUserId(appUser.getId());
			appUserAccount.setBalanceAmount(initialAmount);
			appUserAccount.setCreateDate(now);
			appUserAccount = appUserAccountRepository.save(appUserAccount);
			
			//create account detail for user
			AppUserAccountDetail appUserAccountDetail = new AppUserAccountDetail();
			appUserAccountDetail.setAccountId(appUserAccount.getId());
			appUserAccountDetail.setTransactionAmount(initialAmount);
			appUserAccountDetail.setTransactionType(TransactionType.DEBIT.getValue());
			appUserAccountDetail.setRemarks(appProperties.getDescription());
			appUserAccountDetail.setCreateDate(now);
			appUserAccountDetailRepository.save(appUserAccountDetail);
			
			
		} catch (MissingParameterException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (InvalidUserRoleException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (DuplicateUserNameException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} 
		return appUser;
	}
	
	@Override
	public AppUser getAppUserByName(String userName) throws UserNotFoundException, Exception {
		try {
			AppUser appUser = appUserRepository.findOneByUserName(userName);
			if(appUser == null) {
				throw new UserNotFoundException(String.format("%s is not exist in our system", userName));
			}
			return appUser;
		} catch (UserNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}