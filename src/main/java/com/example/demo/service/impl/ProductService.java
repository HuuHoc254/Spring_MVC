package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InsertProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductMapper;
import com.example.demo.service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> search(String 	code
							, 	String 	productName
							, 	int		page
								) {
		List<Map<String, Object>> map = productMapper.search(
													 	code
													, 	productName
													, 	(page - 1) * 3);
		return map.stream().map(m ->{
			return convertToModel(m);
		}).toList();
	}

	@Override
	public int countSearch( String 	code
						 , 	String 	name) {
		// TODO Auto-generated method stub
		return productMapper.countSearch(code, name);
	}

	private Product convertToModel(Map<String,Object> map){
		Product product = new Product();
		product.setProductId( (Integer) map.get("id"));
		product.setProductCode( (String) map.get("code"));
		product.setProductName( (String) map.get("name"));
		product.setPurchasePrice( (Double) map.get("purchase_price"));
		product.setSalePrice( (Double) map.get("sale_price"));
		product.setInventoryQuantity( (Integer) map.get("inventory_quantity"));
		if( map.containsKey("version")) {
			product.setVersion( (Integer) map.get("version"));
		}
		return product;
	}

	@Override
	public Product getById(int id) {
		return convertToModel(productMapper.getById(id));
	}

	@Override
	public boolean existsByCode(String code) {
		return productMapper.existsByCode(code) > 0;
	}

	@Override
	public boolean existsByName(String name) {
		return productMapper.existsByName(name) > 0;
	}

	@Override
	public boolean create(InsertProduct create) {
		return productMapper.create(create.getProductCode().trim()
								 ,  create.getProductName().trim()
								 , 	create.getPurchasePrice()
								 , 	create.getSalePrice()
								 , 	create.getInventoryQuantity()) > 0;
	}

	@Override
	public boolean existsByCodeNotId(String code, Integer id) {
		return productMapper.existsByCodeNotId(code, id) > 0;
	}
	@Override
	public boolean existsByNameNotId(String name, Integer id) {
		return productMapper.existsByNameNotId(name, id) > 0;
	}

	@Override
	public boolean update(UpdateProduct update) {
		return productMapper.update(update.getProductId()   
								 , 	update.getProductCode().trim()
								 , 	update.getProductName().trim()
								 , 	update.getPurchasePrice()
								 , 	update.getSalePrice()
								 , 	update.getVersion()) > 0;
	}

	@Override
	public boolean delete(int id) {
		return productMapper.delete(id) > 0;
	}

	@Override
	public String getCodeByName(String name) {
		return productMapper.getCodeByName(name);
	}

	@Override
	public String getNameByCode(String code) {
		return productMapper.getNameByCode(code);
	}

	@Override
	public Product getByCode(String code) {
		return convertToModel(productMapper.getByCode(code));
	}
}
