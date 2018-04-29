package com.prime.keeper.assessment.persistence.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prime.keeper.assessment.model.login.AppUserLogin;

@Repository
public interface AppUserLoginRepository extends JpaRepository<AppUserLogin, Integer>, JpaSpecificationExecutor<AppUserLogin> {

	AppUserLogin findOneByUserIdAndUserSession(int userId, String userSession);
	
	AppUserLogin findOneByUserSessionAndUserToken(String userSession, String userToken);
}
