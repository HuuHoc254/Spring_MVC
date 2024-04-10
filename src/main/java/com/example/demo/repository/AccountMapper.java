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
}