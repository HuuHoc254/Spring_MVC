package com.example.demo.dto;

public class Allocation {
    private String  productName;
    private String  productCode;
    private Integer  quantity;
    
	public Allocation() {
		super();
	}
	public Allocation(String productName, String productCode, Integer quantity) {
		super();
		this.productName = productName;
		this.productCode = productCode;
		this.quantity = quantity;
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
