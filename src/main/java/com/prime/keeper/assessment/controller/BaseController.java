package com.prime.keeper.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.prime.keeper.assessment.utils.RequestUtil;

public class BaseController {

	@Autowired
	protected RequestUtil requestUtil;
}
