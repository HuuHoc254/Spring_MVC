package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProductRequest;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.model.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
	List<ProductEntity> searchProduct(String productCode
									, String productName
									, int page );
    int countSearch( String productCode
				   , String productName );
	ProductEntity getProductById(int productId);
	boolean existsByProductCode(String productCode);
	boolean existsByProductName(String productName);
	boolean insertProduct(InsertProductRequest insertProductRequest);
	boolean existsByProductCodeNotId(String productCode, Integer productId);
	boolean existsByProductNameNotId(String productName, Integer productId);
	boolean updateProduct(UpdateProductRequest updateProductRequest);
}
