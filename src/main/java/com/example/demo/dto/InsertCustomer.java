package com.example.demo.dto;

public class InsertCustomer {
    private String  customerName;
    private String  phoneNumber;
    private String  address;
    private Integer accountId;

	public InsertCustomer() {
	}

	public InsertCustomer(String customerName, String phoneNumber, String address, Integer accountId) {
		super();
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
