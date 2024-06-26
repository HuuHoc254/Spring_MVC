package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int countSearch(  String 	accountName
    				, String 	fullName
    				, String 	productCode
    				, String 	productName
    				, String 	customerName
    				, String 	phone
    				, String 	beginDate
    				, String 	endDate
    				, boolean 	orderStatus
    				, boolean 	allocationStatus
    				, Integer	accountId
    				, boolean 	isAdmin
    				);
    List<Map<String, Object>> search( String 	accountName
									, String 	fullName
									, String 	productCode
									, String 	productName
									, String 	customerName
									, String 	phone
									, String 	beginDate
									, String 	endDate
									, boolean 	orderStatus
									, boolean 	allocationStatus
									, Integer	accountId
									, boolean 	isAdmin
									, int		offset
									);
    int update(	int 	productId
			,	double 	unitPrice
			,	int 	quantity
			,	int 	customerId
			,	String 	address
			,	String 	phone
			,	int 	version
			,	int 	id);

	int create( int 	productId
			,	double 	unitPrice
			,	int 	quantity
			,	int 	customerId
			,	String 	address
			,	String 	phone
			,	int 	accountId);

	void allocateInventory(int productId,int quantityToAdd );
	int totalCustomerZeroOrder(String beginDate, String endDate);
	List<Map<String,Object>> customerZeroOrder(String beginDate, String endDate, int offset);
	int totalProductBestSeller(String beginDate, String endDate);
	List<Map<String,Object>> productBestSeller(String beginDate, String endDate, int offset);
	int totalProductZeroOrder(String beginDate, String endDate);
	List<Map<String,Object>> productZeroOrder(String beginDate, String endDate, int offset);
}