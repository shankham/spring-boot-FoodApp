package com.usk.demo.dto;

public class PurchaseProductRequest {

	private String userId;

	private Long fromAcc;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(Long fromAcc) {
		this.fromAcc = fromAcc;
	}

	
}
