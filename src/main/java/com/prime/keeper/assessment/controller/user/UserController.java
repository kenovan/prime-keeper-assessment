package com.prime.keeper.assessment.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.AuthController;
import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.account.AppUserAccount;
import com.prime.keeper.assessment.model.common.ApiResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;
import com.prime.keeper.assessment.model.login.AppUserLogin;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.persistence.login.AppUserLoginRepository;
import com.prime.keeper.assessment.service.user.UserRegistrationService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController extends AuthController {

	@Autowired
	private AppUserLoginRepository appUserLoginRepository;
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@GetMapping(value = "/lookup")
	public ApiResponse userlookup(@RequestParam(name = "username", required = true) String userName) throws UserNotFoundException, Exception {
		AppUser	appUser = userRegistrationService.getAppUserByName(userName);
		for(AppUserAccount appUserAccount : appUser.getAppUserAccounts()) {
			appUserAccount.setBalanceAmount(null);
		}
		return new ApiResponse(ApiResponseExceptionCode.SUCCESS.getCode(), appUser);
	}
	
	@GetMapping(value = "/info")
	public ApiResponse userInfo() {
		AppUserLogin appUserLogin = appUserLoginRepository.findOneByUserSessionAndUserToken(requestUtil.getSessionId(), requestUtil.getAuthToken());
		AppUser appUser = appUserLogin.getAppUser();
		return new ApiResponse(ApiResponseExceptionCode.SUCCESS.getCode(), appUser);
	}
	
}
