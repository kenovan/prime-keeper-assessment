package com.prime.keeper.assessment.service.transaction;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.prime.keeper.assessment.exception.transaction.AccountInsufficientFundException;
import com.prime.keeper.assessment.exception.transaction.InvalidAccountIdException;
import com.prime.keeper.assessment.exception.transaction.MerchantToMerchantTransferException;
import com.prime.keeper.assessment.exception.transaction.PerTransactionLimitException;
import com.prime.keeper.assessment.exception.transaction.SelfAccountTransferException;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.account.AppUserAccount;

public interface TransactionService {

	AppUserAccount transfer(String userName, int amount) throws PerTransactionLimitException,
			SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException;

	AppUserAccount transfer(int accountId, int amount)
			throws InvalidAccountIdException, UserNotFoundException, PerTransactionLimitException,
			SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException;

}