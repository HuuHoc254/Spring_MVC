package com.example.demo.dto;

public class Allocation {
	private int			index;
    private String  	productName;
    private String  	productCode;
    private int  		quantity;

	public Allocation() {
		super();
	}
	public Allocation(int index, String productName, String productCode, int quantity) {
		super();
		this.index = index;
		this.productName = productName;
		this.productCode = productCode;
		this.quantity = quantity;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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

}
