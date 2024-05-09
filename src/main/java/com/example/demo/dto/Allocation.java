package com.example.demo.dto;

public class Allocation {
	private int			number;
    private String  	productName;
    private String  	productCode;
    private Integer  	quantity;

	public Allocation() {
		super();
	}
	public Allocation(int number, String productName, String productCode, Integer quantity) {
		super();
		this.number = number;
		this.productName = productName;
		this.productCode = productCode;
		this.quantity = quantity;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
