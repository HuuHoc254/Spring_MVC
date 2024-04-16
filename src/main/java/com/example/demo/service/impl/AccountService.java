package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InsertAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.repository.AccountMapper;
import com.example.demo.service.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public Account findByAccountName(String accountName) {
		Map<String, Object> map = accountMapper.findByAccountName(accountName);
		if (map == null) {
			return null;	
		}
		return convertToModel(map);
	}
	private Account convertToModel(Map<String, Object> map) {
		Account account = new Account();
		Role role = new Role();
		account.setAccountId((Integer) map.get("account_id"));
		account.setAccountName((String) map.get("account_name"));
		account.setFullName((String) map.get("full_name"));
		account.setPhoneNumber((String) map.get("phone_number"));
		account.setIsOnline((Boolean) map.get("is_online"));
		account.setPassword((String) map.get("password"));
		account.setIsDeleted((Boolean) map.get("is_deleted"));
		role.setRoleId((Integer) map.get("role_id"));
		role.setRoleName((String) map.get("role_name"));
		account.setVersion((Integer) map.get("version"));
		account.setRole(role);
		return account;
	}
	@Override
	public boolean setOnline(Integer accountId) {
		return accountMapper.setOnline(accountId) > 0;
	}
	@Override
	public List<Account> searchAccount(String accountName, String fullName, String phoneNumber, int page) {
		 List<Map<String, Object>> map = accountMapper.search(accountName
				 											, fullName
				 											, phoneNumber
				 											, (page - 1) * 3
				 											  );
		 return map.stream().map(m ->{
				return convertToModel(m);
			}).toList(); 
	}
	@Override
	public int countSearch(String accountName, String fullName, String phoneNumber) {
		return accountMapper.countSearch(accountName, fullName, phoneNumber);
	}

	@Override
	public Account getAccountById(int accountId) {
		return convertToModel(accountMapper.findById(accountId));
	}
	@Override
	public boolean existsByAccountName(String accountName) {
		return accountMapper.existsByAccountName(accountName) > 0;
	}
	@Override
	public boolean existsByphoneNumber(String phoneNumber) {
		return accountMapper.existsByPhoneNumber(phoneNumber) > 0;
	}
	@Override
	public boolean insertAccount(InsertAccount insertAccount) {
		insertAccount.setPassword(encoder.encode(insertAccount.getPassword()));
		return accountMapper.insertAccount(	insertAccount.getAccountName()
										  , insertAccount.getPassword()
										  , insertAccount.getFullName()
										  , insertAccount.getPhoneNumber()
										  ) > 0;
	}
	@Override
	public boolean existsByAccountNameNotId(String accountName, Integer accountId) {
		return accountMapper.existsByAccountNameNotId(accountName, accountId) > 0;
	}
	@Override
	public boolean existsByPhoneNumberNotId(String phoneNumber, Integer accountId) {
		return accountMapper.existsByPhoneNumberNotId(phoneNumber, accountId) > 0;
	}
	@Override
	public boolean updateAccount(UpdateAccount updateAccount) {
		return accountMapper.updateAccount( updateAccount.getAccountId()
										  , updateAccount.getAccountName()
										  , updateAccount.getFullName()
										  , encoder.encode(updateAccount.getPassword())
										  , updateAccount.getPhoneNumber()
										  , updateAccount.getVersion()
											) > 0;
	}
	@Override
	public boolean deleteAccount(int accountId) {
		return accountMapper.deleteAccount(accountId) > 0;
	}
}