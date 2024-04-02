package com.example.demo.service.impl;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.IProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductEntity> searchProduct(SearchRequest searchRequest) {
		List<Map<String, Object>> map = productRepository.search(
													  searchRequest.getProductCode()
													, searchRequest.getProductName()
													, searchRequest.getPageNumber());
		return map.stream().map(m ->{
			return convertMapToProduct(m);
		}).toList();
	}

	@Override
	public int countSearch(SearchRequest request) {
		// TODO Auto-generated method stub
		return productRepository.countSearch(request.getProductCode(), request.getProductName());
	}

	private ProductEntity convertMapToProduct(Map<String,Object> map){
		ProductEntity product = new ProductEntity();
		product.setProductId( (Integer) map.get("product_id"));
		product.setProductCode( (String) map.get("product_code"));
		product.setProductName( (String) map.get("product_name"));
		product.setPurchasePrice( (Double) map.get("purchase_price"));
		product.setSalePrice( (Double) map.get("sale_price"));
		product.setInventoryQuantity( (Integer) map.get("inventory_quantity"));
		return product;
	}
}
