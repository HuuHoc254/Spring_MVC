package com.example.demo.dto;

public class SaveOrder {
    private int  	index;
    private int 	orderId;
    private String  productCode;
    private int 	quantity;
    private String 	phoneNumber;
    private int		version;
    
	public SaveOrder() {
		super();
	}

	public SaveOrder(int index, int orderId, String productCode, int quantity, String phoneNumber, int version) {
		super();
		this.index = index;
		this.orderId = orderId;
		this.productCode = productCode;
		this.quantity = quantity;
		this.phoneNumber = phoneNumber;
		this.version = version;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
}
