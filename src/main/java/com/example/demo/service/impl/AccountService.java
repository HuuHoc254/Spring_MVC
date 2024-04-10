package com.example.demo.service.impl;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.AccountMapper;
import com.example.demo.service.IAccountService;

import java.util.List;
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
		account.setFullName((String) map.get("full_name"));
		account.setPhoneNumber((String) map.get("phone_number"));
		account.setIsOnline((Boolean) map.get("is_online"));
		account.setPassword((String) map.get("password"));
		account.setIsDeleted((Boolean) map.get("is_deleted"));
		role.setRoleId((Integer) map.get("role_id"));
		role.setRoleName((String) map.get("role_name"));
		account.setRole(role);
		return account;
	}
	@Override
	public boolean setOnline(Integer accountId) {
		return accountMapper.setOnline(accountId) > 0;
	}
	@Override
	public List<AccountEntity> searchAccount(String accountName, String fullName, String phoneNumber, int page) {
		 List<Map<String, Object>> map = accountMapper.search(accountName
				 											, fullName
				 											, phoneNumber
				 											, (page - 1) * 3
				 											  );
		 return map.stream().map(m ->{
				return convertToEntity(m);
			}).toList(); 
	}
	@Override
	public int countSearch(String accountName, String fullName, String phoneNumber) {
		return accountMapper.countSearch(accountName, fullName, phoneNumber);
	}
}