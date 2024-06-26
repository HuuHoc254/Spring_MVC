package com.example.demo.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/phone")
    private String getPhoneNumberByName(@RequestParam String name) {
    	return customerService.getPhoneByName(name);
    }

    @GetMapping("/name")
    private String getCustomerNameByPhoneNumber(@RequestParam String phone) {
    	return customerService.getNameByPhone(phone);
    }
}
