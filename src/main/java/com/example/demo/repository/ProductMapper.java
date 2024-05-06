package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int countSearch(String code, String name);
    List<Map<String, Object>> search(String code, String name, int offset);

	Map<String, Object> getById(int id);

	int existsByCode(String code);

	int existsByName(String name);

	int create(	String 	code
			, 	String 	name
			, 	Double 	purchasePrice
			, 	Double 	salePrice
			, 	Integer inventoryQuantity);

	int existsByCodeNotId(String code, Integer id);

	int existsByNameNotId(String name, Integer id);

	int update(	Integer id
			, 	String 	code
			, 	String 	name
			, 	Double 	purchasePrice
			, 	Double 	salePrice
			, 	Integer version);
	int delete(int id);
	String getNameByCode(String code);
	String getCodeByName(String name);
	Map<String, Object> getByCode(String code);
}