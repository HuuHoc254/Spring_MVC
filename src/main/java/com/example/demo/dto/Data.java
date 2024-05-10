package com.example.demo.dto;

public class Data<T> {
	private T data;
	private int totalPage;
	public Data() {
		super();
	}
	public Data(T data, int totalPage) {
		super();
		this.data = data;
		this.totalPage = totalPage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
