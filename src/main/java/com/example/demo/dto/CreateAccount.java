package com.example.demo.dto;

public class CreateAccount {
    private String  name;
    private String  password;
    private String  confirmPassword;
    private String  fullName;
    private String  phone;

	public CreateAccount() {
	}

	public CreateAccount(String name, String password, String confirmPassword, String fullName,
			String phone) {
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.fullName = fullName;
		this.phone = phone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name.trim();
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password.trim();
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword.trim();
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName.trim();
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone.trim();
	}

}
