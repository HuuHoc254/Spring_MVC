package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.InsertAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.model.Account;

public interface IAccountService {
	Account findByName(String name);
	boolean setOnline(Integer id);
	List<Account> search(	String 	name
					 	,	String 	fullName
					 	,	String 	phone
						, 	int 	page );
	int countSearch(  String name
					, String fullName
					, String phone
					);
	Account getById(int id);
	boolean existsByName(String name);
	boolean existsByPhone(String phone);
	boolean create(InsertAccount create);
	boolean existsByNameNotId(String name, Integer id);
	boolean existsByPhoneNotId(String phone, Integer id);
	boolean update(UpdateAccount update);
	boolean delete(int id);
}