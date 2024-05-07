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
    private String getCodeByName(@RequestParam String name) {
    	return productService.getCodeByName(name);
    }

    @GetMapping("/name")
    private String getNameByCode(@RequestParam String code) {
    	String name =  productService.getNameByCode(code);
    	return name;
    }
}
