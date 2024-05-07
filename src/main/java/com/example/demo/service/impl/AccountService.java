package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateAccount;
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
	public Account findByName(String name) {
		Map<String, Object> map = accountMapper.findByName(name);
		if (map == null) {
			return null;	
		}
		return convertToModel(map);
	}
	private Account convertToModel(Map<String, Object> map) {
		Account account = new Account();
		Role role = new Role();
		account.setAccountId((Integer) map.get("id"));
		account.setAccountName((String) map.get("name"));
		account.setFullName((String) map.get("full_name"));
		account.setPhoneNumber((String) map.get("phone"));
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
	public List<Account> search(String name, String fullName, String phone, int page) {
		 List<Map<String, Object>> map = accountMapper.search(name
				 											, fullName
				 											, phone
				 											, (page - 1) * 3
				 											  );
		 return map.stream().map(m ->{
				return convertToModel(m);
			}).toList(); 
	}
	@Override
	public int countSearch(String name, String fullName, String phone) {
		return accountMapper.countSearch(name, fullName, phone);
	}

	@Override
	public Account getById(int id) {
		return convertToModel(accountMapper.findById(id));
	}
	@Override
	public boolean existsByName(String name) {
		return accountMapper.existsByName(name) > 0;
	}
	@Override
	public boolean existsByPhone(String phone) {
		return accountMapper.existsByPhone(phone) > 0;
	}
	@Override
	public boolean create(CreateAccount account) {
		account.setPassword(encoder.encode(account.getPassword()));
		return accountMapper.create(account.getAccountName().trim()
								  , account.getPassword()
								  , account.getFullName().trim()
								  , account.getPhoneNumber().trim()
								  	) > 0;
	}
	@Override
	public boolean existsByNameNotId(String name, Integer id) {
		return accountMapper.existsByNameNotId(name, id) > 0;
	}
	@Override
	public boolean existsByPhoneNotId(String phone, Integer id) {
		return accountMapper.existsByPhoneNotId(phone, id) > 0;
	}
	@Override
	public boolean update(UpdateAccount update) {
		return accountMapper.update(update.getId()
								  , update.getName().trim()
								  , update.getFullName().trim()
								  , encoder.encode(update.getPassword())
								  , update.getPhone().trim()
								  , update.getVersion()
									) > 0;
	}
	@Override
	public boolean delete(int id) {
		return accountMapper.delete(id) > 0;
	}
}