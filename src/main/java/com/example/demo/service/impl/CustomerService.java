package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.InsertCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerMapper;
import com.example.demo.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	private Customer convertToModel(Map<String, Object> map) {
		Customer customer = new Customer();
		Account account = new Account();
		if(map==null) {
			return null;
		}
		account.setAccountId((Integer) map.get("account_id"));
		account.setFullName((String) map.get("full_name"));
		customer.setAccount(account);
		customer.setCustomerId((Integer) map.get("customer_id"));
		customer.setCustomerName((String) map.get("customer_name"));
		customer.setPhoneNumber((String) map.get("phone_number"));
		customer.setAddress((String) map.get("address"));
		customer.setVersion((Integer) map.get("version"));
		return customer;
	}

	@Override
	public List<Customer> searchCustomer(String customerName, String phoneNumber, int page) {
		List<Map<String,Object>> customers = customerMapper.search(customerName, phoneNumber, (page - 1) * 3);
		return customers.stream().map(m-> {return convertToModel(m);}).toList();
	}

	@Override
	public int countSearch(String customerName, String phoneNumber) {
		return customerMapper.countSearch(customerName, phoneNumber);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return convertToModel(customerMapper.findById(customerId));
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return customerMapper.existsByPhoneNumber(phoneNumber) > 0;
	}

	@Override
	public boolean existsByPhoneNumberNotId(String phoneNumber, Integer customerId) {
		return customerMapper.existsByPhoneNumberNotId(phoneNumber, customerId) > 0;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		return customerMapper.deleteCustomer(customerId) > 0;
	}

	@Override
	public boolean insertCustomer(InsertCustomer insertCustomer) {
		return customerMapper.insertCustomer( insertCustomer.getCustomerName()
											, insertCustomer.getPhoneNumber()
											, insertCustomer.getAddress()
											, insertCustomer.getAccountId()
											) >0;
	}

	@Override
	public boolean updateCustomer(UpdateCustomer updateCustomer) {
		return customerMapper.updateCustomer( updateCustomer.getCustomerId()
											, updateCustomer.getCustomerName()
											, updateCustomer.getPhoneNumber()
											, updateCustomer.getAddress()
											, updateCustomer.getVersion()
											) >0;
	}

	@Override
	public String getPhoneNumberByName(String customerName) {
		return customerMapper.getPhoneNumberByName(customerName);
	}

	@Override
	public String getCustomerNameByPhoneNumber(String phoneNumber) {
		return customerMapper.getCustomerNameByPhoneNumber(phoneNumber);
	}

}