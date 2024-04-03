package com.example.demo.controller;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProductRequest;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.service.IProductService;
import com.example.demo.validate.ProductValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidate validate;

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
	    model.addAttribute("isAdmin", true);
	    model.addAttribute("currentPage", 1);
	    model.addAttribute("pageNumbers",pageNumbers);
	    model.addAttribute("searchRequest", searchRequest);

	    return "product-list";
    }

    @PostMapping
    private String searchProduct(Model model, @ModelAttribute SearchRequest searchRequest,  @RequestParam(defaultValue = "1") int page){
    	searchRequest.setPageNumber(page);
        int totalRecord = productService.countSearch(searchRequest);
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPage = totalRecord / 3;
        if(totalRecord % 3 != 0) totalPage++;
        for (int i = 1; i <= totalPage; i++) {
            pageNumbers.add(i);
        }

        List<ProductEntity> products = productService.searchProduct(searchRequest);
        model.addAttribute("isAdmin", true);
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        model.addAttribute("searchRequest",searchRequest);
        model.addAttribute("pageNumbers",pageNumbers);
        return "product-list";
    }

    @GetMapping("/insert")
    private String showFormInsert(Model model){
    	model.addAttribute("insertProductRequest",new InsertProductRequest());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
	    return "product-add";
    }

    @PostMapping("/insert")
    private String insertProduct(Model model, @ModelAttribute InsertProductRequest insertProductRequest){
    	Map<String, String> mapErrors = validate.validateInsertProduct(insertProductRequest);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("insertProductRequest", new InsertProductRequest());
    		if(productService.insertProduct(insertProductRequest)) {
    			model.addAttribute("message", "Thêm mới sản phẩm thành công!");
    		};
    	} else {
    		model.addAttribute("insertProductRequest",insertProductRequest);
    	}

    	model.addAttribute("mapErrors",mapErrors);	
    	return "product-add";
    	
    }

    @GetMapping("/update/{productId}")
    private String showFormUpdate(Model model, @PathVariable("productId") int productId){
    	ProductEntity product = productService.getProductById(productId);
    	model.addAttribute("product", product);
	    return "product-update";
    }

}
