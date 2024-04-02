package com.example.demo.controller;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    
    @GetMapping
    private String showProducts(Model model){
    	SearchRequest searchRequest = new SearchRequest(null,null,1);
    	int totalRecord = productService.countSearch(searchRequest);
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPage = totalRecord / 3;
        if(totalRecord % 3 != 0) totalPage++;
        for (int i = 1; i <= totalPage; i++) {
            pageNumbers.add(i);
        }
	    List<ProductEntity> products = productService.searchProduct(searchRequest); 
	    model.addAttribute("products", products);
	    model.addAttribute("isAdmin", false);
	    model.addAttribute("currentPage", 1);
	    model.addAttribute("pageNumbers",pageNumbers);
	    model.addAttribute("searchRequest", searchRequest);
	    
	    return "product-list";
    }
    
    @PostMapping
    private String searchProduct(Model model, @ModelAttribute SearchRequest searchRequest,  @RequestParam(defaultValue = "1") int page){
    	String message = null;
    	searchRequest.setPageNumber(page);
        int totalRecord = productService.countSearch(searchRequest);
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPage = totalRecord / 3;
        if(totalRecord % 3 != 0) totalPage++;
        for (int i = 1; i <= totalPage; i++) {
            pageNumbers.add(i);
        }
        List<ProductEntity> products = productService.searchProduct(searchRequest);
        model.addAttribute("isAdmin", false);
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        model.addAttribute("searchRequest",searchRequest);
        model.addAttribute("pageNumbers",pageNumbers);
        return "product-list";
    }
    
    @GetMapping("/insert")
    private String showFormInsert(Model model){
	    return "product-add";
    }
}
