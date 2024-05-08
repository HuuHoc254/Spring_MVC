package com.example.demo.model;

public class Account {   
    private Integer id;
    private String name;
    private String fullName;
    private String password;
    private String phone;
    private Role role;
    private Boolean isOnline;
    private Integer version;
    private Boolean isDeleted;
	public Account(Integer id, String name, String fullName, String password, String phone,
			Role role, Boolean isOnline, Integer version, Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.fullName = fullName;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.isOnline = isOnline;
		this.version = version;
		this.isDeleted = isDeleted;
	}
	public Account() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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