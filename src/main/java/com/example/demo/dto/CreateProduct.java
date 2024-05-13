package com.example.demo.dto;

public class CreateProduct {
    private String  code;
    private String  name;
    private Double purchasePrice;
    private Double salePrice;
    private Integer inventoryQuantity;

	public CreateProduct() {
	}
	
	public CreateProduct(String code, String name, Double purchasePrice, Double salePrice, Integer inventoryQuantity) {
		this.code = code;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.inventoryQuantity = inventoryQuantity;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code.trim();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

}
