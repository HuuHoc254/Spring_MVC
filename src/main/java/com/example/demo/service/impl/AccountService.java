package com.example.demo.service.impl;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.AccountMapper;
import com.example.demo.service.IAccountService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
	@Autowired
	AccountMapper accountMapper;

	@Override
	public AccountEntity findByAccountName(String accountName) {
		Map<String, Object> map = accountMapper.findByAccountName(accountName);
		if (map == null) {
			return null;	
		}
		return convertToEntity(map);
	}
	private AccountEntity convertToEntity(Map<String, Object> map) {
		AccountEntity account = new AccountEntity();
		RoleEntity role = new RoleEntity();
		account.setAccountId((Integer) map.get("account_id"));
		account.setAccountName((String) map.get("account_name"));
		account.setPassword((String) map.get("password"));
		account.setIsDeleted((Boolean) map.get("is_deleted"));
		role.setRoleId((Integer) map.get("role_id"));
		role.setRoleName((String) map.get("role_name"));
		account.setRole(role);
		return account;
	}
}