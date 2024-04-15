package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int countSearch(String productCode, String productName);
    List<Map<String, Object>> search(String productCode, String productName, int offset);

	Map<String, Object> getProductById(int productId);

	int existsByProductCode(String productCode);

	int existsByProductName(String productName);

	int insertProduct(String productCode
					, String productName
					, Double purchasePrice
					, Double salePrice
					, Integer inventoryQuantity);

	int existsByProductCodeNotId(String productCode, Integer productId);

	int existsByProductNameNotId(String productName, Integer productId);

	int updateProduct(Integer productId
					, String productCode
					, String productName
					, Double purchasePrice
					, Double salePrice
					, Integer version);
	int deleteProduct(int productId);
	String getProductNameByCode(String productCode);
	String getProductCodeByName(String productName);
}