package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CreateProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.model.Product;

public interface IProductService {
	List<Product> search(	String code
					 	,	String name
						, 	int 	page
						);
    int countSearch( String code
				   , String name
				   );
	Product getById(int id);
	boolean existsByCode(String code);
	boolean existsByName(String name);
	boolean create(CreateProduct create);
	boolean existsByCodeNotId(String code, Integer id);
	boolean existsByNameNotId(String name, Integer id);
	boolean update(UpdateProduct update);
	boolean delete(int id);
	String  getCodeByName(String name);
	String  getNameByCode(String code);
	Product getByCode(String code);
}
