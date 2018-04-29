package com.prime.keeper.assessment.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.AuthController;
import com.prime.keeper.assessment.model.common.ApiResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;
import com.prime.keeper.assessment.service.login.UserLoginService;

@RestController
@RequestMapping(value = "/api/logout")
public class LogoutController extends AuthController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping(value = {"", "/"})
	public ApiResponse logout() {
		boolean hadLoggedOut = userLoginService.logout();
		return new ApiResponse(ApiResponseExceptionCode.SUCCESS.getCode(), hadLoggedOut);
	}
}
