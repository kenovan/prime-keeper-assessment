package com.prime.keeper.assessment.exception.login;

public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 2246519224652775788L;

	public InvalidPasswordException(String message) {
		super(message);
	}
}
