package com.prime.keeper.assessment.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prime.keeper.assessment.exception.transaction.AccountInsufficientFundException;
import com.prime.keeper.assessment.exception.transaction.InvalidAccountIdException;
import com.prime.keeper.assessment.exception.transaction.MerchantToMerchantTransferException;
import com.prime.keeper.assessment.exception.transaction.PerTransactionLimitException;
import com.prime.keeper.assessment.exception.transaction.SelfAccountTransferException;
import com.prime.keeper.assessment.model.exception.ExceptionResponse;
import com.prime.keeper.assessment.model.exception.registration.ApiResponseExceptionCode;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class TransactionControllerAdvise {

    @ExceptionHandler(value = AccountInsufficientFundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleAccountInsufficientFundException(AccountInsufficientFundException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.ACCOUNT_INSUFFICIENT_FUND.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = InvalidAccountIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleInvalidAccountIdException(InvalidAccountIdException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.INVALID_ACCOUNT_ID.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = MerchantToMerchantTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMerchantToMerchantTransferException(MerchantToMerchantTransferException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.MERCHANT_TO_MERCHAT_TRANSFER.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = PerTransactionLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handlePerTransactionLimitException(PerTransactionLimitException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.PER_TRANSACTION_LIMIT.getCode(), e.getMessage());
    }
    
    @ExceptionHandler(value = SelfAccountTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleSelfAccountTransferException(SelfAccountTransferException e) {
        return new ExceptionResponse(ApiResponseExceptionCode.SELF_ACCOUNT_TRANSFER_EXCEPTION.getCode(), e.getMessage());
    }
    
}
