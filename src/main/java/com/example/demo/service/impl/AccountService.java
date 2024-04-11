package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.model.request.InsertAccount;
import com.example.demo.model.request.UpdateAccount;
import com.example.demo.repository.AccountMapper;
import com.example.demo.security.UserDetailImpl;
import com.example.demo.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	private final String ADMIN = "ROLE_ADMIN";
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	PasswordEncoder encoder;

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
		account.setVersion((Integer) map.get("version"));
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
	@Override
	public boolean isAdmin() {
		UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getAuthorities().toString().contains(ADMIN);
	}
	@Override
	public AccountEntity getAccountById(int accountId) {
		return convertToEntity(accountMapper.findById(accountId));
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
	public boolean existsByphoneNumberNotId(String phoneNumber, Integer accountId) {
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