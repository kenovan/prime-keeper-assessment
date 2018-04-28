package com.prime.keeper.assessment.service.user;

import com.prime.keeper.assessment.exception.common.MissingParameterException;
import com.prime.keeper.assessment.exception.user.DuplicateUserNameException;
import com.prime.keeper.assessment.exception.user.InvalidUserRoleException;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.user.AppUser;

public interface UserRegistrationService {

	void registerUser(String userName, String password, String roleName)
			throws MissingParameterException, InvalidUserRoleException, DuplicateUserNameException, Exception;

	AppUser getAppUserByName(String userName) throws UserNotFoundException, Exception;

}