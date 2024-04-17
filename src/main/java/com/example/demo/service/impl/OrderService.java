package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderMapper;
import com.example.demo.service.IOrderService;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public int countSearch(String accountName
						,  String fullName
						,  String productCode
						,  String productName
						,  String customerName
						,  String phoneNumber
						,  String beginOrderDate
						,  String endOrderDate
						,  String orderStatus
						,  String allocationStatus
						,  Integer accountId
						,  boolean isAdmin) {
		
		return orderMapper.countSearch(accountName
									,  fullName
									,  productCode
									,  productName
									,  customerName
									,  phoneNumber
									,  beginOrderDate
									,  endOrderDate
									,  orderStatus.equals("on")
									,  allocationStatus.equals("on")
									,  accountId
									,  isAdmin);
	}

	@Override
	public List<Order> searchCustomer( String accountName
									,  String fullName
									,  String productCode
									,  String productName
									,  String customerName
									,  String phoneNumber
									,  String beginOrderDate
									,  String endOrderDate
									,  String orderStatus
									,  String allocationStatus
									,  Integer accountId
									,  boolean isAdmin
									,  int page) {
		List<Map<String,Object>> map = orderMapper.search(accountName
														, fullName
														, productCode
														, productName
														, customerName
														, phoneNumber
														, beginOrderDate
														, endOrderDate
														, orderStatus.equals("on")
														, allocationStatus.equals("on")
														, accountId
														, isAdmin
														, (page-1) *3);

		return map.stream().map(m ->{ return convertToModel(m);}).toList();
	}
	private Order convertToModel(Map<String, Object> map) {
		Order order = new Order();
		order.setOrderId((int) map.get("order_id"));
		order.setAccountName((String) map.get("account_name"));
		order.setFullName((String) map.get("full_name"));
		order.setCustomerName((String) map.get("customer_name"));
		order.setPhoneNumberCustomer((String) map.get("phone_number_customer"));
		order.setAddressCustomer((String) map.get("address_customer"));
		order.setProductCode((String) map.get("product_code"));
		order.setProductName((String) map.get("product_name"));
		order.setUnitPrice((Double) map.get("unit_price"));
		order.setQuantity((Integer) map.get("quantity"));
		order.setOrderStatusName((String) map.get("order_status_name"));
		order.setOrderDate((LocalDateTime) map.get("order_date"));
		order.setVersion((int) map.get("version"));
		if(map.get("order_date")!=null) {
			order.setAllocationDate((LocalDateTime) map.get("allocation_date"));
		}
		return order;
}

	
}