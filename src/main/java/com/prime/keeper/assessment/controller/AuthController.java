package com.prime.keeper.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.prime.keeper.assessment.utils.AuthenticationUtil;

public class AuthController extends BaseController {

	@Autowired
	protected AuthenticationUtil authenticationUtil;
}
