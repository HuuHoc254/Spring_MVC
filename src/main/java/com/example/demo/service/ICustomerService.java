package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.InsertCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.model.Customer;

public interface ICustomerService {
	List<Customer> searchCustomer(String 	customerName
								, String 	phoneNumber
								, int 		page );
	int countSearch(  String 	customerName
					, String 	phoneNumber
					);
	Customer getCustomerById(int customerId);
	boolean existsByPhoneNumber(String phoneNumber);
	boolean existsByPhoneNumberNotId(String phoneNumber, Integer customerId);
	boolean deleteCustomer(int customerId);
	boolean insertCustomer(InsertCustomer insertCustomer);
	boolean updateCustomer(UpdateCustomer updateCustomer);
	String getPhoneNumberByName(String customerName);
	String getCustomerNameByPhoneNumber(String phoneNumber);
	Customer getCustomerByPhoneNumber(String phoneNumber);
}