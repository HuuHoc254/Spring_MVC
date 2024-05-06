package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    int countSearch(  String 	name
    				, String 	phone
    				);
    List<Map<String, Object>> search( String 	name
									, String 	phone
									, int 		offset
									);

	Map<String, Object> findById(int id);

	int existsByPhone(String phone);

	int create(	String 	name
			  , String 	phone
			  ,	String 	address
			  , int 	accountId
				);

	int existsByPhoneNotId(String phone, Integer id);

	int update(	Integer id
			, 	String 	name
			, 	String 	phone
			, 	String 	address
			, 	Integer version);

	int delete(int customerId);
	String getPhoneByName(String name);
	String getNameByPhone(String phone);
	Map<String, Object> findByPhone(String phone);
}