package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AccountEntity;

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
	}
