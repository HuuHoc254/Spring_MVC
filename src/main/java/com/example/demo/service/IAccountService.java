package com.example.demo.service;

import com.example.demo.entity.AccountEntity;

public interface IAccountService {
	AccountEntity findByAccountName(String accountName);
}
