package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerMapper;
import com.example.demo.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer convertToModel(Map<String, Object> map) {
		if( map == null ) {
			throw new RuntimeException("Khách hàng không tồn tại!");
		}
		Customer customer = new Customer();
		Account account = new Account();
		account.setId((Integer) map.get("id"));
		account.setFullName((String) map.get("full_name"));
		customer.setAccount(account);
		customer.setId((Integer) map.get("id"));
		customer.setName((String) map.get("name"));
		customer.setPhone((String) map.get("phone"));
		customer.setAddress((String) map.get("address"));
		customer.setVersion((Integer) map.get("version"));
		return customer;
	}

	@Override
	public List<Customer> search(String name, String phone, int page) {
		List<Map<String,Object>> customers = customerMapper.search(name, phone, (page - 1) * 3);
		return customers.stream().map(m-> {return convertToModel(m);}).toList();
	}

	@Override
	public int countSearch(String customerName, String phone) {
		return customerMapper.countSearch(customerName, phone);
	}

	@Override
	public Customer getById(int id) {
		return convertToModel(customerMapper.findById(id));
	}

	@Override
	public boolean existsByPhone(String phone) {
		return customerMapper.existsByPhone(phone) > 0;
	}

	@Override
	public boolean existsByPhoneNotId(String phone, Integer id) {
		return customerMapper.existsByPhoneNotId(phone, id) > 0;
	}

	@Override
	public boolean delete(int id) {
		return customerMapper.delete(id) > 0;
	}

	@Override
	public boolean create(CreateCustomer create) {
		return customerMapper.create( create.getName()
									, create.getPhone()
									, create.getAddress()
									, create.getAccountId()
									) >0;
	}

	@Override
	public boolean update(UpdateCustomer update) {
		return customerMapper.update( update.getId()
									, update.getName()
									, update.getPhone()
									, update.getAddress()
									, update.getVersion()
									) >0;
	}

	@Override
	public String getPhoneByName(String name) {
		return customerMapper.getPhoneByName(name);
	}

	@Override
	public String getNameByPhone(String phone) {
		return customerMapper.getNameByPhone(phone);
	}

	@Override
	public Customer getByPhone(String phone) {
		return convertToModel(customerMapper.findByPhone(phone));
	}

}