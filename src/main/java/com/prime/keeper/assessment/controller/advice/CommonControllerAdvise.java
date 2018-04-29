package com.prime.keeper.assessment.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prime.keeper.assessment.exception.common.MissingParameterException;
import com.prime.keeper.assessment.exception.common.UnauthorizedException;
import com.prime.keeper.assessment.model.exception.ExceptionResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;

@ControllerAdvice
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class CommonControllerAdvise {
	
	@ExceptionHandler(value = UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ExceptionResponse handleUnauthorizedException(UnauthorizedException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.UNAUTHORIZED_REQUEST.getCode(), e.getMessage());
    }
	
	@ExceptionHandler(value = MissingParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMissingParameterException(MissingParameterException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.MISSING_PARAMETER.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleException(Exception e) {
        return new ExceptionResponse(ApiResponseExceptionCode.GENERAL_EXCEPTION.getCode(), e.getMessage());
    }
}
