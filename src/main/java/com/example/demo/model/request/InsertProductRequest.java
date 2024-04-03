package com.example.demo.model.request;

public class InsertProductRequest {
    private String  productCode;
    private String  productName;
    private Double purchasePrice;
    private Double salePrice;
    private Integer inventoryQuantity;
    
    
	public InsertProductRequest() {
	}
	
	public InsertProductRequest(String productCode, String productName, Double purchasePrice, Double salePrice, Integer inventoryQuantity) {
		this.productCode = productCode;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.inventoryQuantity = inventoryQuantity;
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
