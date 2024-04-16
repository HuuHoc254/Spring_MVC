package com.example.demo.model;

import java.time.LocalDateTime;

public class Order {
    private Integer orderId;
    private String productCode;
    private String productName;
    private Double unitPrice;
    private Integer quantity;
    private String customerName;
    private String addressCustomer;
    private String phoneNumberCustomer;
    private String accountName;
    private String fullName;
    private LocalDateTime orderDate;
    private LocalDateTime allocationDate;
    private String orderStatusName;
    private Integer version;
    
	public Order() {
		super();
	}

	public Order(Integer orderId, String productCode, String productName, Double unitPrice, Integer quantity,
			String customerName, String addressCustomer, String phoneNumberCustomer, String accountName,
			String fullName, LocalDateTime orderDate, LocalDateTime allocationDate, String orderStatusName,
			Integer version) {
		super();
		this.orderId = orderId;
		this.productCode = productCode;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.customerName = customerName;
		this.addressCustomer = addressCustomer;
		this.phoneNumberCustomer = phoneNumberCustomer;
		this.accountName = accountName;
		this.fullName = fullName;
		this.orderDate = orderDate;
		this.allocationDate = allocationDate;
		this.orderStatusName = orderStatusName;
		this.version = version;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}

	public String getPhoneNumberCustomer() {
		return phoneNumberCustomer;
	}

	public void setPhoneNumberCustomer(String phoneNumberCustomer) {
		this.phoneNumberCustomer = phoneNumberCustomer;
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

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(LocalDateTime allocationDate) {
		this.allocationDate = allocationDate;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}