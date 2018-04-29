package com.prime.keeper.assessment.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptPasswordUtil {

	private static final Logger log = LoggerFactory.getLogger(EncryptPasswordUtil.class);
	
	private static final String ALGORITHM = "SHA-1";

	private static final String ENCODING = "UTF-8";

	public static String encrypt(String password) {
		MessageDigest msgDigest = null;
		String hashValue = StringUtils.EMPTY;
		try {
			msgDigest = MessageDigest.getInstance(ALGORITHM);
			msgDigest.update(password.getBytes(ENCODING));
			byte rawByte[] = msgDigest.digest();
			hashValue = Base64.getMimeEncoder().encodeToString(rawByte);

		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return hashValue;
	}

}
