package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AccountEntity;
import com.example.demo.model.request.InsertAccount;
import com.example.demo.model.request.UpdateAccount;

public interface IAccountService {
	AccountEntity findByAccountName(String accountName);
	boolean setOnline(Integer accountId);
	List<AccountEntity> searchAccount(String accountName
									, String fullName
									, String phoneNumber
									, int page );
	int countSearch(  String accountName
					, String fullName
					, String phoneNumber);
	
	boolean isAdmin();
	AccountEntity getAccountById(int accountId);
	boolean existsByAccountName(String accountName);
	boolean existsByphoneNumber(String phoneNumber);
	boolean insertAccount(InsertAccount insertAccountRequest);
	boolean existsByAccountNameNotId(String accountName, Integer accountId);
	boolean existsByphoneNumberNotId(String phoneNumber, Integer accountId);
	boolean updateAccount(UpdateAccount updateAccount);
	boolean deleteAccount(int accountId);
}