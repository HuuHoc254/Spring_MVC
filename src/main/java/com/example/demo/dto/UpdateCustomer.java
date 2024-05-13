package com.example.demo.dto;

public class UpdateCustomer {
	private Integer id;
    private String  name;
    private String  phone;
    private String  address;
    private Integer accountId;
    private Integer version;

	public UpdateCustomer() {
	}

	public UpdateCustomer(Integer id, String name, String phone, String address,
			Integer accountId, Integer version) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.accountId = accountId;
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
		this.name = name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	

}
