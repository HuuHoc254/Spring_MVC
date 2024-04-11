//package com.example.demo.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entity.AccountEntity;
//import com.example.demo.entity.Customer;
//import com.example.demo.entity.RoleEntity;
//import com.example.demo.repository.CustomerMapper;
//import com.example.demo.service.ICustomerService;
//
//@Service
//public class CustomerService implements ICustomerService {
//	@Autowired
//	private CustomerMapper customerMapper;
//
//	private Customer convertToEntity(Map<String, Object> map) {
//		Customer customer = new Customer();
//		AccountEntity account = new AccountEntity();
//		account.setAccountId((Integer) map.get("account_id"));
//		account.setFullName((String) map.get("full_name"));
//		customer.setAccount(account);
//		customer.setA((String) map.get("account_name"));
//		customer.setAddress((String) map.get("phone_number"));
//		customer.setAddress((String) map.get("address"));
//		return account;
//	}
//
//	@Override
//	public List<AccountEntity> searchAccount(String customerName, String phoneNumber, int page) {
//		return customerMapper.search(customerName, phoneNumber, page);
//	}
//
//	@Override
//	public int countSearch(String customerName, String phoneNumber) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean isAdmin() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public AccountEntity getCustomerById(int customerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsByphoneNumber(String phoneNumber) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean existsByphoneNumberNotId(String phoneNumber, Integer customerId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteCustomer(int customerId) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}