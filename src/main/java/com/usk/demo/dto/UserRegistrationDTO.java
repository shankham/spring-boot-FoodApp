package com.usk.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "Role is required")
	private String Role;

	@Email
	@NotNull
	private String EmailId;

	@NotBlank(message = "Mobile No is required")
	private String mobileNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return EmailId;
	}

	public void setEmailId(String emailId) {
		this.EmailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
