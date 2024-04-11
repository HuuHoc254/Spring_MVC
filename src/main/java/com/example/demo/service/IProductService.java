package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProduct;
import com.example.demo.model.request.UpdateProduct;

public interface IProductService {
	List<ProductEntity> searchProduct(String productCode
									, String productName
									, int page );
    int countSearch( String productCode
				   , String productName );
	ProductEntity getProductById(int productId);
	boolean existsByProductCode(String productCode);
	boolean existsByProductName(String productName);
	boolean insertProduct(InsertProduct insertProductRequest);
	boolean existsByProductCodeNotId(String productCode, Integer productId);
	boolean existsByProductNameNotId(String productName, Integer productId);
	boolean updateProduct(UpdateProduct updateProductRequest);
	boolean deleteProduct(int productId);
	boolean isAdmin();
}
