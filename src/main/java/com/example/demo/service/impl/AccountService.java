package com.example.demo.service.impl;

import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountMapper;
import com.example.demo.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
	@Autowired
	AccountMapper accountMapper;

	@Override
	public AccountEntity findByAccountName(String accountName) {
		return accountMapper.findByAccountName(accountName);
	}

}