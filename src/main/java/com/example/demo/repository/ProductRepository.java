package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.example.demo.provider.ProductProvider;

@Mapper
public interface ProductRepository {
    @SelectProvider(type = ProductProvider.class, method = "countSearch")
    int countSearch(String productCode, String productName);

    @SelectProvider(type = ProductProvider.class, method = "search")
    List<Map<String, Object>> search(String productCode, String productName, int pageNumber);
}