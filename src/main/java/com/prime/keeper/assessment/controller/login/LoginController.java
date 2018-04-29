package com.prime.keeper.assessment.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.BaseController;
import com.prime.keeper.assessment.exception.login.InvalidPasswordException;
import com.prime.keeper.assessment.exception.login.UserHasLoggedException;
import com.prime.keeper.assessment.model.login.AppUserLogin;
import com.prime.keeper.assessment.service.login.UserLoginService;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController extends BaseController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping(value = {"", "/"})
	public AppUserLogin login(@RequestParam(name = "username", required = true) String userName
			, @RequestParam(name = "password", required = true) String userPassword) throws UserHasLoggedException, InvalidPasswordException, Exception {
		return userLoginService.login(userName, userPassword);
	}
}
