package com.prime.keeper.assessment.model.common;

public class ApiResponse {

	private String code;
	
	private Object data;

	public ApiResponse() {
		
	}
	
	public ApiResponse(String code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
