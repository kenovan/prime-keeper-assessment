package com.prime.keeper.assessment.model.exception.registration;

public enum ApiResponseExceptionCode {

	SUCCESS("000"),
	GENERAL_EXCEPTION("001"),
	MISSING_PARAMETER("002"),
	INVALID_USER_ROLE("003"),
	DUPLICATE_USER_NAME("004"),
	USER_NOT_FOUND("005"),
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
