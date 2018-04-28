package com.prime.keeper.assessment.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prime.keeper.assessment.exception.user.DuplicateUserNameException;
import com.prime.keeper.assessment.exception.user.InvalidUserRoleException;
import com.prime.keeper.assessment.model.exception.ExceptionResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RegistrationControllerAdvise {

    @ExceptionHandler(value = InvalidUserRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleInvalidUserRoleException(InvalidUserRoleException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.INVALID_USER_ROLE.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = DuplicateUserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleDuplicateUserNameException(DuplicateUserNameException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.DUPLICATE_USER_NAME.getCode(), e.getMessage());
    }
    
}
