package com.prime.keeper.assessment.exception.transaction;

public class AccountInsufficientFundException extends Exception {

	private static final long serialVersionUID = 3419404427501712287L;

	public AccountInsufficientFundException(String message) {
		super(message);
	}
}
