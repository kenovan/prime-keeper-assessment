package com.prime.keeper.assessment.exception.login;

public class UserNotAuthorizedException extends Exception {

	private static final long serialVersionUID = 6155265122262836732L;
	
	public UserNotAuthorizedException(String message) {
		super(message);
	}

}
