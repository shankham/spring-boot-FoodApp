package com.usk.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "userAccountNumber")
public class UserAccountNumber {
	
	private Long userId;
	
	private Long accountNumber;
	
	private float balance;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	
	
	

}
