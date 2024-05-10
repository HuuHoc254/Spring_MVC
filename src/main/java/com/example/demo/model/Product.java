package com.example.demo.model;

public class Product {
   
    private Integer id;
    private String code;
    private String name;
    private Double purchasePrice;
    private Double salePrice;
    private Integer inventoryQuantity;
    private Integer version;
    private Boolean isDeleted;
    
	public Product() {
	}
	
	public Product(Integer id, String code, String name, Double purchasePrice,
			Double salePrice, Integer inventoryQuantity, Integer version, Boolean isDeleted) {
		this.id 				= id;
		this.code 				= code;
		this.name 				= name;
		this.purchasePrice 		= purchasePrice;
		this.salePrice 			= salePrice;
		this.inventoryQuantity 	= inventoryQuantity;
		this.version 			= version;
		this.isDeleted 			= isDeleted;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
