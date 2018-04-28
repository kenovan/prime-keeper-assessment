package com.prime.keeper.assessment.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prime.keeper.assessment.controller.BaseController;
import com.prime.keeper.assessment.model.join.AppUserRole;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.persistence.user.AppUserRepository;
import com.prime.keeper.assessment.service.properties.AppPropertiesService;

@RestController
public class RegisterController extends BaseController {

	@Autowired
	private AppPropertiesService appPropertiesService; 
	
	@Autowired AppUserRepository appUserRepository;
	
	@GetMapping(value="/test")
	public AppUser testController() {
		AppUser appUser = appUserRepository.findByUserName("kenovan");
		AppUser newUser = new AppUser();
		newUser.setUserName("test 123");
		newUser.setUserPassword("test123123");
		newUser = appUserRepository.save(newUser);
		AppUserRole appUserRole = new AppUserRole();
		appUserRole.setUserId(newUser.getId());
		appUserRole.setRoleId(1);
		
		return appUser;
	}
}
