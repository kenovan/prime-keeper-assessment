package com.prime.keeper.assessment.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

	private static final String KEY = "testing-accessment";
	
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public String generateToken(String userName, String sessionId, Date now) {
		String formatToken = String.format("%s-%s-%s-%s", userName, sessionId, KEY, DATEFORMAT.format(now));
		return EncryptPasswordUtil.encrypt(formatToken);
	}
}
