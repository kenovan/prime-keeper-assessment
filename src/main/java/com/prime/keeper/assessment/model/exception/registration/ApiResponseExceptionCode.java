package com.prime.keeper.assessment.model.exception.registration;

public enum ApiResponseExceptionCode {

	SUCCESS("000"),
	GENERAL_EXCEPTION("001"),
	MISSING_PARAMETER("002"),
	INVALID_USER_ROLE("003"),
	DUPLICATE_USER_NAME("004"),
	USER_NOT_FOUND("005"),
	UNAUTHORIZED_REQUEST("006"),
	INVALID_PASSWORD("007"),
	USER_HAS_LOGGED("008"),
	AUTHENTICATION_TOKEN_EXPIRED("009"),
	ACCOUNT_INSUFFICIENT_FUND("010"),
	INVALID_ACCOUNT_ID("011"),
	MERCHANT_TO_MERCHAT_TRANSFER("012"),
	PER_TRANSACTION_LIMIT("013"),
	SELF_ACCOUNT_TRANSFER_EXCEPTION("014"),
	;
	
	private String code;
	
	private ApiResponseExceptionCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
