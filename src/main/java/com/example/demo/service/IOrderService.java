package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SaveOrder;
import com.example.demo.model.Order;

public interface IOrderService {
	int countSearch(  String 	accountName
					, String 	fullName
					, String 	productCode
					, String 	productName
					, String 	customerName
					, String 	phoneNumber
					, String 	beginOrderDate
					, String 	endOrderDate
					, String 	orderStatus
					, String 	allocationStatus
					, Integer	accountId
					, boolean 	isAdmin
					);
	
	List<Order> searchCustomer(String 	accountName
							,  String 	fullName
							,  String 	productCode
							,  String 	productName
							,  String 	customerName
							,  String 	phoneNumber
							,  String 	beginOrderDate
							,  String 	endOrderDate
							,  String 	orderStatus
							,  String 	allocationStatus
							,  Integer	accountId
							,  boolean 	isAdmin
							,  int		page
							);

	void saveOrder(List<SaveOrder> orders);
}