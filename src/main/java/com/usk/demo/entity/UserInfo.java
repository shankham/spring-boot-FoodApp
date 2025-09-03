package com.usk.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserInfo {

	private String userId;

	private String userName;
	
	private String password;
	
	private String Role;

	private String emailId;

	private String mobileNo;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/*
	 * 
	 * 
	 * public UserInfo(Long userId, String userName, String password, String
	 * firstName, String lastName, int age, String gender, String emailId, String
	 * mobileNo) { super(); this.userId = userId; this.userName = userName;
	 * this.password = password; this.firstName = firstName; this.lastName =
	 * lastName; this.age = age; this.gender = gender; EmailId = emailId;
	 * this.mobileNo = mobileNo; }
	 */
	
	
}
