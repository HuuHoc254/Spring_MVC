package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.InsertProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.model.Product;

public interface IProductService {
	List<Product> searchProduct(String productCode
									, String productName
									, int page );
    int countSearch( String productCode
				   , String productName );
	Product getProductById(int productId);
	boolean existsByProductCode(String productCode);
	boolean existsByProductName(String productName);
	boolean insertProduct(InsertProduct insertProductRequest);
	boolean existsByProductCodeNotId(String productCode, Integer productId);
	boolean existsByProductNameNotId(String productName, Integer productId);
	boolean updateProduct(UpdateProduct updateProductRequest);
	boolean deleteProduct(int productId);
}
