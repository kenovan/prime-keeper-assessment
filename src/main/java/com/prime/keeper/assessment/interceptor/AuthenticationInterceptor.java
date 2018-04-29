package com.prime.keeper.assessment.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.prime.keeper.assessment.controller.AuthController;
import com.prime.keeper.assessment.exception.common.UnauthorizedException;
import com.prime.keeper.assessment.exception.login.AuthenticationTokenExpiredException;
import com.prime.keeper.assessment.model.login.AppUserLogin;
import com.prime.keeper.assessment.persistence.login.AppUserLoginRepository;
import com.prime.keeper.assessment.utils.RequestUtil;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AppUserLoginRepository appUserLoginRepository;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws AuthenticationTokenExpiredException, UnauthorizedException, Exception {
		if(object != null) {
			HandlerMethod handlerMethod = (HandlerMethod) object;
			Object bean = handlerMethod.getBean();
			if(AuthController.class.isAssignableFrom(bean.getClass())) {
				String sessionId = request.getSession().getId();
				String authToken = request.getHeader(RequestUtil.AUTH_TOKEN_NAME);
				if(StringUtils.isBlank(authToken)) {
					throw new UnauthorizedException("Authentication Object is empty.");
				} else {
					AppUserLogin appUserLogin = appUserLoginRepository.findOneByUserSessionAndUserToken(sessionId, authToken);
					if(new Date().after(appUserLogin.getTokenExpired())) {
						throw new AuthenticationTokenExpiredException("Authentication token is expired, please relogin.");
					}
				}
				
			}
		}
		return true;
    }
	
}
