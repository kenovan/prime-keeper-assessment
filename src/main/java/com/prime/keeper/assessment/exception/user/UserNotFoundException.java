package com.prime.keeper.assessment.exception.user;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 4579291660795624310L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
