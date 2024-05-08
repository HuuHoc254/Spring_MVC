package com.example.demo.rest_controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaveOrder;
import com.example.demo.service.IOrderService;
import com.example.demo.validate.OrderValidate;

@RestController
@RequestMapping("/api/order/save")
public class OrderRestController {
	@Autowired
	private OrderValidate validate;
	@Autowired
	private IOrderService orderService;
	@PostMapping
	public ResponseEntity<Map<Integer, Map<String, String>>> save(@RequestBody List<SaveOrder> orders) {
	    Map<Integer, Map<String, String>> mapErrors = validate.save(orders);
	    if(mapErrors.isEmpty()) {
	    	orderService.saveOrder(orders);
	    	return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	}
}
