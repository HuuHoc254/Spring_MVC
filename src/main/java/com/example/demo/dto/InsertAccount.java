package com.example.demo.dto;

public class InsertAccount {
    private String  accountName;
    private String  password;
    private String  confirmPassword;
    private String  fullName;
    private String  phoneNumber;

	public InsertAccount() {
	}

	public InsertAccount(String accountName, String password, String confirmPassword, String fullName,
			String phoneNumber) {
		this.accountName = accountName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
