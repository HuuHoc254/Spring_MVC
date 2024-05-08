package com.example.demo.model;

public class Customer {
    private Integer id;
    private String name;
    private String phone;
    private Account account;
    private String address;
    private Integer version;
    
	public Customer() {
		super();
	}
	public Customer(Integer id, String name, String phone, Account account, String address,
			Integer version) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.account = account;
		this.address = address;
		this.version = version;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
    
}