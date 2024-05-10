package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.CreateCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.model.Customer;

public interface ICustomerService {
	List<Customer> search(String 	name
						, String 	phone
						, int 		page
						);
	int countSearch(String 	name
				, 	String 	phone
					);
	Customer getById(int id);
	boolean existsByPhone(String phone);
	boolean existsByPhoneNotId(	String phone
							, 	Integer id
								);
	boolean delete(int id);
	boolean create(CreateCustomer create);
	boolean update(UpdateCustomer update);
	String getPhoneByName(String name);
	String getNameByPhone(String phone);
	Customer getByPhone(String phone);
	Customer convertToModel(Map<String, Object> map);
}