package com.example.demo.model;

public class Product {
   
    private Integer productId;
    private String productCode;
    private String productName;
    private Double purchasePrice;
    private Double salePrice;
    private Integer inventoryQuantity;
    private Integer version;
    private Boolean isDeleted;
    
    
	public Product() {
	}
	
	public Product(Integer productId, String productCode, String productName, Double purchasePrice,
			Double salePrice, Integer inventoryQuantity, Integer version, Boolean isDeleted) {
		this.productId = productId;
		this.productCode = productCode;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.inventoryQuantity = inventoryQuantity;
		this.version = version;
		this.isDeleted = isDeleted;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
}
