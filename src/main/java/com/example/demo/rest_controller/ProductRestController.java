package com.example.demo.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping("/code")
    private String getProductCodeByName(@RequestParam String productName) {
    	return productService.getProductCodeByName(productName);
    }

    @GetMapping("/name")
    private String getProductNameByCode(@RequestParam String productCode) {
    	String name =  productService.getProductNameByCode(productCode);
    	return name;
    }
}
