package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	Map<String, Object> findByName(String name);

	int setOnline(Integer id);
    int countSearch(  String name
    				, String fullName
    				, String phone
    				);
    List<Map<String, Object>> search( String 	name
									, String 	fullName
									, String 	phone
									, int 		offset
									);

	Map<String, Object> findById(int id);

	int existsByName(String name);

	int existsByPhone(String phone);

	int create(String name
					, String password
					, String fullName
					, String phone);

	int existsByNameNotId(String name, Integer id);

	int existsByPhoneNotId(String phone, Integer id);

	int update(	Integer id
			, 	String 	name
			, 	String 	fullName
			, 	String 	password
			, 	String 	phone
			, 	Integer version);

	int delete(int id);
}