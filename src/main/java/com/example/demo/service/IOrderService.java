package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Allocation;
import com.example.demo.dto.ProductBestSellerResponse;
import com.example.demo.dto.SaveOrder;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;

public interface IOrderService {
	int countSearch(  String 	accountName
					, String 	fullName
					, String 	productCode
					, String 	productName
					, String 	customerName
					, String 	phoneNumber
					, String 	beginDate
					, String 	endDate
					, String 	orderStatus
					, String 	allocationStatus
					, Integer	accountId
					, boolean 	isAdmin
					);
	
	List<Order> search(String 	accountName
					,  String 	fullName
					,  String 	productCode
					,  String 	productName
					,  String 	customerName
					,  String 	phoneNumber
					,  String 	beginDate
					,  String 	endDate
					,  String 	orderStatus
					,  String 	allocationStatus
					,  Integer	accountId
					,  boolean 	isAdmin
					,  int		page
						);

	void saveOrder(List<SaveOrder> orders);
	void allocate(List<Allocation> allocaties);

	int totalCustomerZeroOrder(String beginDate, String endDate);
	List<Customer> customerZeroOrder(String beginDate, String endDate, int offset);

	int totalProductBestSeller(String beginDate, String endDate);
	List<ProductBestSellerResponse> productBestSeller(String beginDate, String endDate, int offset);

	int totalProductZeroOrder(String beginDate, String endDate);
	List<Product> productZeroOrder(String beginDate, String endDate, int offset);
}