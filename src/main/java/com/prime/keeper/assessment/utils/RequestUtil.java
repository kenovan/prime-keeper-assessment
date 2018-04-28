package com.prime.keeper.assessment.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestUtil {

	@Autowired
	private HttpServletRequest request;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUserAgent() {
		if (request != null) {
			return StringUtils.isNotBlank(request.getHeader("User-Agent")) ? request.getHeader("User-Agent")
					: StringUtils.EMPTY;
		} else {
			return StringUtils.EMPTY;
		}
	}

	public String getIpAddress() {
		if (request != null) {
			return StringUtils.isNotBlank(request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR")
					: request.getRemoteAddr();
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	public String getSessionId() {
		if (request != null) {
			HttpSession session = request.getSession();
			if(session != null) {
				return session.getId();
			} else {
				return StringUtils.EMPTY;
			}
		} else {
			return StringUtils.EMPTY;
		}
	}
}
