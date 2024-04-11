package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    int countSearch(  String customerName
    				, String phoneNumber
    				);
    List<Map<String, Object>> search( String customerName
									, String phoneNumber
									, int offset
									);

	Map<String, Object> findById(int customerId);

	int existsByPhoneNumber(String phoneNumber);

	int insertCustomer(	String customerName
					  , String phoneNumber
					  ,	String address
					  , int accountId
						);

	int existsByPhoneNumberNotId(String phoneNumber, Integer customerId);

	int updateCustomer(Integer customerId
					, String customerName
					, String phoneNumber
					, String address
					, Integer version);

	int deleteCustomer(int customerId);
}