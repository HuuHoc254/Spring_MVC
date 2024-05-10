package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateProduct;
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
													 	code.trim()
													, 	productName.trim()
													, 	(page - 1) * 3);
		return map.stream().map(m ->{
			return convertToModel(m);
		}).toList();
	}

	@Override
	public int countSearch( String 	code
						 , 	String 	name) {
		return productMapper.countSearch(code.trim(), name.trim());
	}
	@Override
	public Product convertToModel(Map<String,Object> map){
		if(map == null) {
			throw new RuntimeException("Sản phẩm không tồn tại!");
		}
		Product product = new Product();
		product.setId( (Integer) map.get("id"));
		product.setCode( (String) map.get("code"));
		product.setName( (String) map.get("name"));
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
	public boolean create(CreateProduct product) {
		return productMapper.create(product.getCode().trim()
								 ,  product.getName().trim()
								 , 	product.getPurchasePrice()
								 , 	product.getSalePrice()
								 , 	product.getInventoryQuantity()) > 0;
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
		return productMapper.update(update.getId()   
								 , 	update.getCode().trim()
								 , 	update.getName().trim()
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
