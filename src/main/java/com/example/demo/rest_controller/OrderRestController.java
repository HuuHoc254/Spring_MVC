package com.example.demo.rest_controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Data;
import com.example.demo.dto.ProductBestSellerResponse;
import com.example.demo.dto.SaveOrder;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.IOrderService;
import com.example.demo.validate.OrderValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
	@Autowired
	private OrderValidate validate;
	@Autowired
	private IOrderService orderService;
	@PostMapping("/save")
	public ResponseEntity<Map<Integer, Map<String, String>>> save(@RequestBody List<SaveOrder> orders, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    Map<Integer, Map<String, String>> mapErrors = validate.save(orders);
	    if(mapErrors.isEmpty()) {
	    	orderService.saveOrder(orders);
	    	session.setAttribute("successMessage", "Đã lưu đơn hàng thành công!");
	    	return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	}
	@GetMapping("/customerZeroOrder")
    private Data<List<Customer>> customerZeroOrder( @RequestParam(defaultValue = "") String beginDate
	    										, 	@RequestParam(defaultValue = "") String endDate
												, 	@RequestParam(defaultValue = "1") int page) {
		int totalRecord = orderService.totalCustomerZeroOrder(beginDate, endDate);
		int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
    	List<Customer> customers =  orderService.customerZeroOrder(beginDate, endDate, (page-1)*3);
    	Data<List<Customer>> data = new Data<List<Customer>>();
    	data.setData(customers);
    	data.setTotalPage(totalPage);
    	return data;
    }

	@GetMapping("/productBestSeller")
    private Data<List<ProductBestSellerResponse>> productBestSeller(  @RequestParam(defaultValue = "") String beginDate
																	, @RequestParam(defaultValue = "") String endDate
																	, @RequestParam(defaultValue = "1") int page) {
		int totalRecord = orderService.totalProductBestSeller(beginDate, endDate);
		int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
    	List<ProductBestSellerResponse> products =  orderService.productBestSeller(beginDate, endDate, (page-1)*3);
    	Data<List<ProductBestSellerResponse>> data = new Data<List<ProductBestSellerResponse>>();
    	data.setData(products);
    	data.setTotalPage(totalPage);
    	return data;
    }

	@GetMapping("/productZeroOrder")
    private Data<List<Product>> productZeroOrder( @RequestParam(defaultValue = "") String beginDate
												, @RequestParam(defaultValue = "") String endDate
												, @RequestParam(defaultValue = "1") int page) {
		int totalRecord = orderService.totalProductZeroOrder(beginDate, endDate);
		int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
    	List<Product> products =  orderService.productZeroOrder(beginDate, endDate, (page-1)*3);
    	Data<List<Product>> data = new Data<List<Product>>();
    	data.setData(products);
    	data.setTotalPage(totalPage);
    	return data;
    }
}
