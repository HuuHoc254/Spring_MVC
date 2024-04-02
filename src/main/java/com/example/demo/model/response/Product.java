package com.example.demo.model.response;

public class Product {

	private Integer productId;
	private String productCode;
    private String productName;
    private String purchasePrice;
    private String salePrice;
    private Integer inventoryQuantity;
    private Integer version;
    private Boolean isDeleted;
    
	public Product() {
	}

	public Product(Integer productId, String productCode, String productName, String purchasePrice, String salePrice,
			Integer inventoryQuantity, Integer version, Boolean isDeleted) {
		super();
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

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
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
