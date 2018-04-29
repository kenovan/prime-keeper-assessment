package com.prime.keeper.assessment.exception.transaction;

public class PerTransactionLimitException extends Exception {

	private static final long serialVersionUID = 558041328191403723L;

	public PerTransactionLimitException(String message) {
		super(message);
	}
}
