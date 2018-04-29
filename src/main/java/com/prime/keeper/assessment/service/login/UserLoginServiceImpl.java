package com.prime.keeper.assessment.service.login;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.keeper.assessment.exception.login.InvalidPasswordException;
import com.prime.keeper.assessment.exception.login.UserHasLoggedException;
import com.prime.keeper.assessment.model.login.AppUserLogin;
import com.prime.keeper.assessment.model.user.AppUser;
import com.prime.keeper.assessment.persistence.login.AppUserLoginRepository;
import com.prime.keeper.assessment.persistence.user.AppUserRepository;
import com.prime.keeper.assessment.utils.AuthenticationUtil;
import com.prime.keeper.assessment.utils.EncryptPasswordUtil;
import com.prime.keeper.assessment.utils.RequestUtil;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private static final Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private RequestUtil requestUtil;

	@Autowired
	private AuthenticationUtil authenticationUtil;

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private AppUserLoginRepository appUserLoginRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prime.keeper.assessment.service.login.UserLoginService#login(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public AppUserLogin login(String userName, String password)
			throws UserHasLoggedException, InvalidPasswordException, Exception {
		AppUserLogin appUserLogin = null;
		try {
			String sessionId = requestUtil.getSessionId();
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();

			appUserLogin = appUserLoginRepository.findOneByUserSessionAndUserToken(sessionId,
					requestUtil.getAuthToken());
			if (appUserLogin != null) {
				if (now.before(appUserLogin.getTokenExpired())) {
					throw new UserHasLoggedException(String.format(
							"User had logged in. If you wish to change user account, please logout then relogin. Current token: %s",
							appUserLogin.getUserToken()));
				}
			}
			AppUser appUser = appUserRepository.findOneByUserName(userName);
			String encrpyedPassword = EncryptPasswordUtil.encrypt(password);
			if (encrpyedPassword.equals(appUser.getUserPassword()) == false) {
				throw new InvalidPasswordException("Wrong Password Enter.");
			}
			calendar.add(Calendar.HOUR, 1);
			Date tokenExpiredDate = calendar.getTime();
			String generatedToken = authenticationUtil.generateToken(userName, sessionId, now);

			appUserLogin = new AppUserLogin();
			appUserLogin.setUserId(appUser.getId());
			appUserLogin.setUserSession(sessionId);
			appUserLogin.setUserToken(generatedToken);
			appUserLogin.setTokenExpired(tokenExpiredDate);
			appUserLogin.setCreateDate(now);

			appUserLogin = appUserLoginRepository.save(appUserLogin);

		} catch (UserHasLoggedException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (InvalidPasswordException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return appUserLogin;
	}

}
