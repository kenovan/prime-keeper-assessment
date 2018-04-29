package com.prime.keeper.assessment.exception.transaction;

public class SelfAccountTransferException extends Exception {

	private static final long serialVersionUID = -6833214395328022298L;

	public SelfAccountTransferException(String message) {
		super(message);
	}
}
