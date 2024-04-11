package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.request.InsertProduct;
import com.example.demo.model.request.UpdateProduct;
import com.example.demo.service.IProductService;
import com.example.demo.validate.ProductValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ProductController {
	
    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidate validate;

    @GetMapping("/product")
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
        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
        List<ProductEntity> products = productService.searchProduct(productCode, productName, page);
        model.addAttribute("isAdmin", productService.isAdmin());
        model.addAttribute("productCode", productCode);
        model.addAttribute("productName", productName);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("message", session.getAttribute("message"));
        model.addAttribute("url","product");
        session.removeAttribute("message");
        return "product/product-list";
    }

    @GetMapping("/admin/product/insert")
    private String showFormInsert(Model model){
    	model.addAttribute("insertProductRequest",new InsertProduct());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", productService.isAdmin());
	    return "product/product-add";
    }

    @PostMapping("/admin/product/insert")
    private String insertProduct(Model model, @ModelAttribute InsertProduct insertProductRequest){
    	Map<String, String> mapErrors = validate.validateInsertProduct(insertProductRequest);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("insertProductRequest", new InsertProduct());
    		if(productService.insertProduct(insertProductRequest)) {
    			model.addAttribute("message", "Thêm mới sản phẩm thành công!");
    		};
    	} else {
    		model.addAttribute("insertProductRequest",insertProductRequest);
    	}
    	model.addAttribute("isAdmin", productService.isAdmin());
    	model.addAttribute("mapErrors",mapErrors);	
    	return "product/product-add";
    	
    }

    @GetMapping("/admin/product/update/{productId}")
    private String showFormUpdate(Model model, @PathVariable("productId") int productId){
    	ProductEntity product = productService.getProductById(productId);
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("updateProductRequest", product);
    	model.addAttribute("isAdmin", productService.isAdmin());
	    return "product/product-update";
    }
    
    @PostMapping("admin/product/update/{productId}")
    private String updateProduct( HttpServletRequest request
    							, Model model
    							, @ModelAttribute UpdateProduct updateProductRequest
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
			session.setAttribute("message", "Cập nhật sản phẩm thành công!");
			return search;
		}
    	model.addAttribute("updateProductRequest",updateProductRequest); 
    	model.addAttribute("mapErrors",mapErrors);
    	model.addAttribute("isAdmin", productService.isAdmin());
    	return "product/product-update";	
    }
    
    @GetMapping("/admin/product/delete/{productId}")
    private String deleteProduct( HttpServletRequest request
    							, Model model
    							, @PathVariable("productId") int productId){
    	HttpSession session = request.getSession();
    	boolean check = productService.deleteProduct(productId);
    	if (check) {
    		session.setAttribute("message", "Đã xóa sản phẩm thành công!");
    	}else {
    		session.setAttribute("message", "Xóa sản phẩm thất bại, đã có lỗi s!");
    	}
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
	    return search;
    }

}
