package com.prime.keeper.assessment.model.account;

import java.util.HashMap;

public enum TransactionType {

	CREDIT("credit"),
	DEBIT("debit");
	
	private String value;
	
	private static HashMap<String, TransactionType> map = new HashMap<>();
	
	static {
		for(TransactionType type : TransactionType.values()) {
			map.put(type.getValue(), type);
		}
	}
	
	private TransactionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public TransactionType getTransactionType(String value) {
		if(map.containsKey(value)) {
			return map.get(value);
		} else {
			return null;
		}
	}
}
