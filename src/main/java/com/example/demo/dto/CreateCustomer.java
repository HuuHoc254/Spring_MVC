package com.example.demo.dto;

public class CreateCustomer {
    private String  name;
    private String  phone;
    private String  address;
    private Integer accountId;

	public CreateCustomer() {
	}

	public CreateCustomer(String name, String phone, String address, Integer accountId) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
