package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.CustomerController;
import com.example.demo.dto.Allocation;
import com.example.demo.dto.ProductBestSellerResponse;
import com.example.demo.dto.SaveOrder;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderMapper;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IProductService productService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private AuthService authService;
	private static final Logger logger = Logger.getLogger(CustomerController.class);

	@Override
	public int countSearch(String accountName
						,  String fullName
						,  String productCode
						,  String productName
						,  String customerName
						,  String phoneNumber
						,  String beginDate
						,  String endDate
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
									,  beginDate
									,  endDate
									,  orderStatus.equals("on")
									,  allocationStatus.equals("on")
									,  accountId
									,  isAdmin);
	}

	@Override
	public List<Order> search( String accountName
							,  String fullName
							,  String productCode
							,  String productName
							,  String customerName
							,  String customerPhone
							,  String beginDate
							,  String endDate
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
														, customerPhone
														, beginDate
														, endDate
														, orderStatus.equals("on")
														, allocationStatus.equals("on")
														, accountId
														, isAdmin
														, (page-1) *3);

		return map.stream().map(m ->{ return convertToModel(m);}).toList();
	}
	private Order convertToModel(Map<String, Object> map) {
		Order order = new Order();
		order.setOrderId((int) map.get("id"));
		order.setAccountName((String) map.get("account_name"));
		order.setFullName((String) map.get("full_name"));
		order.setCustomerName((String) map.get("customer_name"));
		order.setCustomerPhone((String) map.get("customer_phone"));
		order.setCustomerAddress((String) map.get("customer_address"));
		order.setProductCode((String) map.get("code"));
		order.setProductName((String) map.get("product_name"));
		order.setUnitPrice((Double) map.get("unit_price"));
		order.setQuantity((Integer) map.get("quantity"));
		order.setOrderStatusName((String) map.get("order_status_name"));
		order.setOrderDate((LocalDateTime) map.get("order_date"));
		order.setVersion((int) map.get("version"));
		if(map.get("allocation_date")!=null) {
			order.setAllocationDate((LocalDateTime) map.get("allocation_date"));
		}
		return order;
	}

	public ProductBestSellerResponse convertProductReponseToModel(Map<String,Object> map){
		ProductBestSellerResponse product = new ProductBestSellerResponse();
		product.setId( (Integer) map.get("id"));
		product.setCode( (String) map.get("code"));
		product.setName( (String) map.get("name"));
		BigDecimal quantity = (BigDecimal) map.get("quantity");
		product.setQuantity(quantity.intValue());
		return product;
	}

	@Override
	public void saveOrder(List<SaveOrder> orders) {
		boolean check = false;
		for(SaveOrder order : orders) {
			Product product = productService.getByCode(order.getProductCode());
			Customer customer = customerService.getByPhone(order.getPhone());
			if(order.getOrderId() != null) {
				check = orderMapper.update(	product.getId()
										, 	product.getSalePrice()
										,	order.getQuantity()
										,	customer.getId()
										,	customer.getAddress()
										,	customer.getPhone()
										,	order.getVersion()
										,	order.getOrderId()) > 0;
				if(!check) {
					logger.info("---------------BEGIN-------------");
		    		logger.error("OrderID not exits or Version do not match!");
		    		logger.error("OrderID: "+order.getOrderId());
		    		logger.error("Version: "+order.getVersion());
		    		logger.info("---------------END---------------");
					throw new RuntimeException("Đã có lỗi xảy ra, vui lòng ktra lại!");
				}
			}else {
				check = orderMapper.create(	product.getId()
										,	product.getSalePrice()
										,	order.getQuantity()
										,	customer.getId()
										,	customer.getAddress()
										,	customer.getPhone()
										,	authService.getIdLogin()
											) > 0;
				if(!check) {
					logger.info("---------------BEGIN-------------");
		    		logger.error("Sever Errors!");
		    		logger.error("Create not success!");
		    		logger.info("---------------END---------------");
					throw new RuntimeException("Đã có lỗi xảy ra, vui lòng kiem tra lại!");
				}
			}
		}
	}

	@Override
	public void allocate(List<Allocation> allocaties) {
		for(int i=0;i<allocaties.size()-1;i++) {
			Product product = productService.getByCode(allocaties.get(i).getProductCode());
			orderMapper.allocateInventory(product.getId(),allocaties.get(i).getQuantity());
		}
	}

	@Override
	public List<Customer> customerZeroOrder(String beginDate, String endDate, int offset) {
		List<Map<String, Object>> customers = orderMapper.customerZeroOrder(beginDate, endDate, offset);
		return customers.stream().map(m-> {return customerService.convertToModel(m);}).toList();
	}

	@Override
	public int totalCustomerZeroOrder(String beginDate, String endDate) {
		return orderMapper.totalCustomerZeroOrder(beginDate, endDate);
	}

	@Override
	public int totalProductBestSeller(String beginDate, String endDate) {
		return orderMapper.totalProductBestSeller(beginDate, endDate);
	}

	@Override
	public List<ProductBestSellerResponse> productBestSeller(String beginDate, String endDate, int offset) {
		List<Map<String, Object>> products = orderMapper.productBestSeller(beginDate, endDate, offset);
		return products.stream().map(m-> {return convertProductReponseToModel(m);}).toList();
	}

	@Override
	public int totalProductZeroOrder(String beginDate, String endDate) {
		return orderMapper.totalProductZeroOrder(beginDate, endDate);
	}

	@Override
	public List<Product> productZeroOrder(String beginDate, String endDate, int offset) {
		List<Map<String, Object>> products = orderMapper.productZeroOrder(beginDate, endDate, offset);
		return products.stream().map(m-> {return productService.convertToModel(m);}).toList();
	}

	
}