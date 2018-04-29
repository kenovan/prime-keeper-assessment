package com.prime.keeper.assessment.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.AuthController;
import com.prime.keeper.assessment.exception.transaction.AccountInsufficientFundException;
import com.prime.keeper.assessment.exception.transaction.InvalidAccountIdException;
import com.prime.keeper.assessment.exception.transaction.MerchantToMerchantTransferException;
import com.prime.keeper.assessment.exception.transaction.PerTransactionLimitException;
import com.prime.keeper.assessment.exception.transaction.SelfAccountTransferException;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.account.AppUserAccount;
import com.prime.keeper.assessment.service.transaction.TransactionService;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionController extends AuthController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/send-using-recipient-name")
	public AppUserAccount transferByReceipientName(@RequestParam(name = "username") String userName,
			@RequestParam(name = "amount") Integer amount) throws PerTransactionLimitException, SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException {
		return transactionService.transfer(userName, amount);
	}
	
	@PostMapping(value = "/send-using-recipient-account-id")
	public AppUserAccount transferByReceipientName(@RequestParam(name = "account-id") Integer accountId,
			@RequestParam(name = "amount") Integer amount) throws PerTransactionLimitException, SelfAccountTransferException, MerchantToMerchantTransferException, AccountInsufficientFundException, InvalidAccountIdException, UserNotFoundException {
		return transactionService.transfer(accountId, amount);
	}
}
