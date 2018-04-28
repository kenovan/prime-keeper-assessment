package com.prime.keeper.assessment.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prime.keeper.assessment.exception.user.UserNotFoundException;
import com.prime.keeper.assessment.model.exception.ExceptionResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class UserControllerAdvise {

	@ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.USER_NOT_FOUND.getCode(), e.getMessage());
    }
}
