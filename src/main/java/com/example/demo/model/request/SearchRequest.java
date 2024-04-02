package com.example.demo.model.request;

public class SearchRequest {
    private String  productCode;
    private String  productName;
    private int     pageNumber;
    
	public SearchRequest() {
	}
	
	public SearchRequest(String productCode, String productName, int pageNumber) {
		this.productCode = productCode;
		this.productName = productName;
		this.pageNumber = pageNumber;
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
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
    
}
