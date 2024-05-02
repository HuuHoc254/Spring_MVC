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
@RequestMapping("/api/order/save-order")
public class OrderRestController {
	@Autowired
	private OrderValidate validate;
	@Autowired
	private IOrderService orderService;
	@PostMapping
	public ResponseEntity<Map<Integer, Map<String, String>>> saveOrder(@RequestBody List<SaveOrder> saveOrders) {
	    Map<Integer, Map<String, String>> mapErrors = validate.validateSaveOrder(saveOrders);
	    if(mapErrors.isEmpty()) {
	    	orderService.saveOrder(saveOrders);
	    	return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(mapErrors);
	}
}
