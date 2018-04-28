package com.prime.keeper.assessment.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.BaseController;

@RestController
public class LoginController extends BaseController {

	@GetMapping(value = "/login")
	public String login() {
		return requestUtil.getSessionId();
	}
}
