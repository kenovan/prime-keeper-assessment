package com.prime.keeper.assessment.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prime.keeper.assessment.exception.login.AuthenticationTokenExpiredException;
import com.prime.keeper.assessment.exception.login.InvalidPasswordException;
import com.prime.keeper.assessment.exception.login.UserHasLoggedException;
import com.prime.keeper.assessment.exception.login.UserNotAuthorizedException;
import com.prime.keeper.assessment.model.exception.ExceptionResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class LoginControllerAdvise {
	
	@ExceptionHandler(value = UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleUserNotAuthorizedException(UserNotAuthorizedException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.USER_NOT_AUTHORIZED.getCode(), e.getMessage());
    }

	@ExceptionHandler(value = UserHasLoggedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleUserHasLoggedException(UserHasLoggedException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.USER_HAS_LOGGED.getCode(), e.getMessage());
    }
	
	@ExceptionHandler(value = AuthenticationTokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleAuthenticationTokenExpiredException(AuthenticationTokenExpiredException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.AUTHENTICATION_TOKEN_EXPIRED.getCode(), e.getMessage());
    }
	
	@ExceptionHandler(value = InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleInvalidPasswordException(InvalidPasswordException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.INVALID_PASSWORD.getCode(), e.getMessage());
    }
}
