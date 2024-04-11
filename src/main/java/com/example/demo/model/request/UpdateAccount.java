package com.example.demo.model.request;

public class UpdateAccount {
	private Integer accountId;
    private String  accountName;
    private String  password;
    private String confirmPassword;
    private String fullName;
    private String phoneNumber;
    private Integer version;
    
    
	public UpdateAccount() {
	}


	public UpdateAccount(Integer accountId, String accountName, String password, String confirmPassword,
			String fullName, String phoneNumber, Integer version) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.version = version;
	}


	public Integer getAccountId() {
		return accountId;
	}


	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
