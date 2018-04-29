package com.prime.keeper.assessment.service.login;

import com.prime.keeper.assessment.exception.login.InvalidPasswordException;
import com.prime.keeper.assessment.exception.login.UserHasLoggedException;
import com.prime.keeper.assessment.model.login.AppUserLogin;

public interface UserLoginService {

	AppUserLogin login(String userName, String password)
			throws UserHasLoggedException, InvalidPasswordException, Exception;

}