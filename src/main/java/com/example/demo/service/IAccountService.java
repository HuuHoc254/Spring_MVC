package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.InsertAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.model.Account;

public interface IAccountService {
	Account findByAccountName(String accountName);
	boolean setOnline(Integer accountId);
	List<Account> searchAccount(String accountName
									, String fullName
									, String phoneNumber
									, int page );
	int countSearch(  String accountName
					, String fullName
					, String phoneNumber);
	Account getAccountById(int accountId);
	boolean existsByAccountName(String accountName);
	boolean existsByphoneNumber(String phoneNumber);
	boolean insertAccount(InsertAccount insertAccountRequest);
	boolean existsByAccountNameNotId(String accountName, Integer accountId);
	boolean existsByPhoneNumberNotId(String phoneNumber, Integer accountId);
	boolean updateAccount(UpdateAccount updateAccount);
	boolean deleteAccount(int accountId);
}