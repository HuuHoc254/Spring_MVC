package com.example.demo.dto;

public class SaveOrder {
    private int  	index;
    private Integer orderId;
    private String  productCode;
    private int 	quantity;
    private String 	phone;
    private int		version;
    
	public SaveOrder() {
		super();
	}

	public SaveOrder(int index, Integer orderId, String productCode, int quantity, String phone, int version) {
		super();
		this.index = index;
		this.orderId = orderId;
		this.productCode = productCode;
		this.quantity = quantity;
		this.phone = phone;
		this.version = version;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
}
