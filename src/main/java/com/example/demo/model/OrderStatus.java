package com.example.demo.model;

public class OrderStatus {
    private Integer orderStatusId;
    private String orderStatusName;
	public OrderStatus() {
		super();
	}
	public OrderStatus(Integer orderStatusId, String orderStatusName) {
		super();
		this.orderStatusId = orderStatusId;
		this.orderStatusName = orderStatusName;
	}
	public Integer getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
    
}