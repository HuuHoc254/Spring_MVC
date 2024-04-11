package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	Map<String, Object> findByAccountName(String accountName);

	int setOnline(Integer accountId);
    int countSearch(  String accountName
    				, String fullName
    				, String phoneNumber
    				);
    List<Map<String, Object>> search( String accountName
									, String fullName
									, String phoneNumber
									, int offset
									);

	Map<String, Object> findById(int accountId);

	int existsByAccountName(String accountName);

	int existsByPhoneNumber(String phoneNumber);

	int insertAccount(String accountName
					, String password
					, String fullName
					, String phoneNumber);

	int existsByAccountNameNotId(String accountName, Integer accountId);

	int existsByPhoneNumberNotId(String phoneNumber, Integer accountId);

	int updateAccount(Integer accountId
					, String accountName
					, String fullName
					, String password
					, String phoneNumber
					, Integer version);

	int deleteAccount(int accountId);
}