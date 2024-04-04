package com.example.demo.service.impl;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProductRequest;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.model.request.UpdateProductRequest;
import com.example.demo.repository.ProductMapper;
import com.example.demo.service.IProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductEntity> searchProduct( String 	productCode
											, String 	productName
											, int		page) {
		List<Map<String, Object>> map = productMapper.search(
													 productCode
													, productName
													, (page - 1) * 3);
		return map.stream().map(m ->{
			return convertMapToProduct(m);
		}).toList();
	}

	@Override
	public int countSearch(String 	productCode
						 , String 	productName) {
		// TODO Auto-generated method stub
		return productMapper.countSearch(productCode,productName);
	}

	private ProductEntity convertMapToProduct(Map<String,Object> map){
		ProductEntity product = new ProductEntity();
		product.setProductId( (Integer) map.get("product_id"));
		product.setProductCode( (String) map.get("product_code"));
		product.setProductName( (String) map.get("product_name"));
		product.setPurchasePrice( (Double) map.get("purchase_price"));
		product.setSalePrice( (Double) map.get("sale_price"));
		product.setInventoryQuantity( (Integer) map.get("inventory_quantity"));
		if( map.containsKey("version")) {
			product.setVersion( (Integer) map.get("version"));
		}
		return product;
	}

	@Override
	public ProductEntity getProductById(int productId) {
		return convertMapToProduct(productMapper.getProductById(productId));
	}

	@Override
	public boolean existsByProductCode(String productCode) {
		return productMapper.existsByProductCode(productCode) > 0;
	}

	@Override
	public boolean existsByProductName(String productName) {
		// TODO Auto-generated method stub
		return productMapper.existsByProductName(productName) > 0;
	}

	@Override
	public boolean insertProduct(InsertProductRequest insertProductRequest) {
		return productMapper.insertProduct(insertProductRequest.getProductCode()
										 , insertProductRequest.getProductName()
										 , insertProductRequest.getPurchasePrice()
										 , insertProductRequest.getSalePrice()
										 , insertProductRequest.getInventoryQuantity()) > 0;
	}

	@Override
	public boolean existsByProductCodeNotId(String productCode, Integer productId) {
		return productMapper.existsByProductCodeNotId(productCode,productId) > 0;
	}
	@Override
	public boolean existsByProductNameNotId(String productName, Integer productId) {
		return productMapper.existsByProductNameNotId(productName,productId) > 0;
	}

	@Override
	public boolean updateProduct(UpdateProductRequest updateProductRequest) {
		return productMapper.updateProduct(updateProductRequest.getProductId()   
										 , updateProductRequest.getProductCode()
										 , updateProductRequest.getProductName()
										 , updateProductRequest.getPurchasePrice()
										 , updateProductRequest.getSalePrice()
										 , updateProductRequest.getVersion()) > 0;
	}
}
