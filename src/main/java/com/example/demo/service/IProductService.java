package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.SearchRequest;
import java.util.List;

public interface IProductService {
	List<ProductEntity> searchProduct(SearchRequest searchRequest);
    int countSearch(SearchRequest request);
}
