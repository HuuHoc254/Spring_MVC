package com.example.demo.dto;

public class ProductBestSellerResponse {
    private Integer id;
    private String code;
    private String name;
    private Integer quantity;
	public ProductBestSellerResponse() {
		super();
	}
	public ProductBestSellerResponse(Integer id, String code, String name, Integer quantity) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.quantity = quantity;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    
}
