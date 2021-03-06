package com.prime.keeper.assessment.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.BaseController;
import com.prime.keeper.assessment.exception.common.MissingParameterException;
import com.prime.keeper.assessment.exception.user.DuplicateUserNameException;
import com.prime.keeper.assessment.exception.user.InvalidUserRoleException;
import com.prime.keeper.assessment.model.common.ApiResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.service.user.UserRegistrationService;

@RestController
@RequestMapping(value = "/api/register")
public class RegisterController extends BaseController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(value = {"", "/"})
	public ApiResponse userRegistration(@RequestParam(name = "username", required = true) String userName,
			@RequestParam(name = "password", required = true) String userPassword,
			@RequestParam(name = "role", required = true) String userRole) throws MissingParameterException, InvalidUserRoleException, DuplicateUserNameException, Exception {
		AppUser appUser = userRegistrationService.registerUser(userName, userPassword, userRole);
		return new ApiResponse(ApiResponseExceptionCode.SUCCESS.getCode(), appUser);
	}
	
}
