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

import com.example.demo.dto.InsertProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.ProductValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ProductController {
	@Autowired
	private AuthService authService;
	
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
    	if(page < 1) {
    		page = 1;
    	}
    	if( productCode != "") {
    		session.setAttribute("productCode", productCode);
    	}
    	if( productName != "") {
    		session.setAttribute("productName", productName);
    	}
    	
    	session.setAttribute("currentPage", page);
    	
        int totalRecord = productService.countSearch( productCode, productName );
        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
        List<Product> products = productService.searchProduct(productCode, productName, page);
        model.addAttribute("isAdmin", authService.isAdmin());
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

    @GetMapping("/product/cancel")
    private String cancel( HttpServletRequest request, Model model){
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
		return search;
    }

    @GetMapping("/admin/product/insert")
    private String showFormInsert(Model model){
    	model.addAttribute("insertProduct",new InsertProduct());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "product/product-add";
    }

    @PostMapping("/admin/product/insert")
    private String insertProduct(Model model, @ModelAttribute InsertProduct insertProduct){
    	Map<String, String> mapErrors = validate.validateInsertProduct(insertProduct);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("insertProductRequest", new InsertProduct());
    		if(productService.insertProduct(insertProduct)) {
    			model.addAttribute("message", "Thêm mới sản phẩm thành công!");
    		};
    	} else {
    		model.addAttribute("insertProduct",insertProduct);
    	}
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("mapErrors",mapErrors);	
    	return "product/product-add";
    	
    }

    @GetMapping("/admin/product/update/{productId}")
    private String showFormUpdate(Model model, @PathVariable("productId") int productId){
    	Product product = productService.getProductById(productId);
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("updateProduct", product);
    	model.addAttribute("isAdmin", authService.isAdmin());
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
    	model.addAttribute("isAdmin", authService.isAdmin());
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
