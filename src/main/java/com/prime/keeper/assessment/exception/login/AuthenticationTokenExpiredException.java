package com.prime.keeper.assessment.exception.login;

public class AuthenticationTokenExpiredException extends Exception {

	private static final long serialVersionUID = 4611111808704232317L;
	
	public AuthenticationTokenExpiredException(String message) {
		super(message);
	}

}
