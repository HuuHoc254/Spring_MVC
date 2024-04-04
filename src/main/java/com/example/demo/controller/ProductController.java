package com.example.demo.controller;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProductRequest;
import com.example.demo.model.request.SearchRequest;
import com.example.demo.model.request.UpdateProductRequest;
import com.example.demo.service.IProductService;
import com.example.demo.validate.ProductValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    private String searchProduct(HttpServletRequest request
    							, Model model
    							, @RequestParam(defaultValue = "") String productCode
    							, @RequestParam(defaultValue = "") String productName
    							, @RequestParam(defaultValue = "1") int page
    							){
    	HttpSession session = request.getSession();
    	if( productCode != "") {
    		session.setAttribute("productCode", productCode);
    	}
    	if( productName != "") {
    		session.setAttribute("productName", productName);
    	}

    	session.setAttribute("currentPage", page);
    	
        int totalRecord = productService.countSearch( productCode, productName );
        List<Integer> pageNumbers = new ArrayList<>();
        int totalPage = totalRecord / 3;
        if(totalRecord % 3 != 0) totalPage++;
        for (int i = 1; i <= totalPage; i++) {
            pageNumbers.add(i);
        }

        List<ProductEntity> products = productService.searchProduct(productCode, productName, page);
        model.addAttribute("isAdmin", true);
        model.addAttribute("productCode", productCode);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
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
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("updateProductRequest", product);
	    return "product-update";
    }
    
    @PostMapping("/update/{productId}")
    private String updateProduct( HttpServletRequest request
    							, Model model
    							, @ModelAttribute UpdateProductRequest updateProductRequest
    							, @PathVariable int productId
    							){
    	HttpSession session = request.getSession();
    	String pCodeSearch = (String) session.getAttribute("productCode");
    	String pNameSearch = (String) session.getAttribute("productName");
    	int page = (int) session.getAttribute("currentPage");
    	String search = "redirect:/product?page="+page;
    	if(pCodeSearch!=null) {
    		search += "&productCode="+pCodeSearch;
    	}
    	if(pNameSearch!=null) {
    		search += "&productName="+pNameSearch;
    	}
    	
    	Map<String, String> mapErrors = validate.validateUpdateProduct(updateProductRequest);
		if( mapErrors.size() == 0 && productService.updateProduct(updateProductRequest)) {
			model.addAttribute("message", "Cập nhật sản phẩm thành công!");
			return search;
		}
    	model.addAttribute("updateProductRequest",updateProductRequest); 
    	model.addAttribute("mapErrors",mapErrors);	
    	return "product-update";	
    }

}
