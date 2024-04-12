package com.example.demo.model;

public class Account {   
    private Integer accountId;
    private String accountName;
    private String fullName;
    private String password;
    private String phoneNumber;
    private Role role;
    private Boolean isOnline;
    private Integer version;
    private Boolean isDeleted;
	public Account(Integer accountId, String accountName, String fullName, String password, String phoneNumber,
			Role role, Boolean isOnline, Integer version, Boolean isDeleted) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.fullName = fullName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.isOnline = isOnline;
		this.version = version;
		this.isDeleted = isDeleted;
	}
	public Account() {
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	
    
}