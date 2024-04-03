package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProductRequest;
import com.example.demo.model.request.SearchRequest;

import java.util.List;

public interface IProductService {
	List<ProductEntity> searchProduct(SearchRequest searchRequest);
    int countSearch(SearchRequest request);
	ProductEntity getProductById(int productId);
	boolean existsByProductCode(String productCode);
	boolean existsByProductName(String productName);
	boolean insertProduct(InsertProductRequest insertProductRequest);
}
