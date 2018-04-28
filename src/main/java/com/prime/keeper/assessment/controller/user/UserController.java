package com.prime.keeper.assessment.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.common.ApiResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.service.user.UserRegistrationService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@GetMapping(value = "/info")
	public ApiResponse userRegistration(@RequestParam(name = "userName", required = true) String userName) throws UserNotFoundException, Exception {
		AppUser	appUser = userRegistrationService.getAppUserByName(userName);
		return new ApiResponse(ApiResponseExceptionCode.SUCCESS.getCode(), appUser);
	}
	
}
