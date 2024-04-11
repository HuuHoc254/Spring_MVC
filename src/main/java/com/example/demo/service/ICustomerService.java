package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AccountEntity;

public interface ICustomerService {
	List<AccountEntity> searchAccount(String customerName
									, String phoneNumber
									, int page );
	int countSearch(  String customerName
					, String phoneNumber);
	boolean isAdmin();
	AccountEntity getCustomerById(int customerId);
	boolean existsByphoneNumber(String phoneNumber);
//	boolean insertCustomer(InsertCustomer insertAccountRequest);
	boolean existsByphoneNumberNotId(String phoneNumber, Integer customerId);
//	boolean updateAccount(UpdateAccount updateAccount);
	boolean deleteCustomer(int customerId);
}