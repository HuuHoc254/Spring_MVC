//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.demo.entity.ProductEntity;
//import com.example.demo.service.IProductService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/customer")
//public class CustomerController {
//	
//    @Autowired
//    private IProductService productService;
//
//    @GetMapping
//    private String searchProduct(HttpServletRequest request
//    							, Model model
//    							, @RequestParam(defaultValue = "") String customerName
//    							, @RequestParam(defaultValue = "") String phoneNumber
//    							, @RequestParam(defaultValue = "1") int page
//    							){
//    	HttpSession session = request.getSession();
//    	if( customerName != "") {
//    		session.setAttribute("customerName", customerName);
//    	}
//    	if( phoneNumber != "") {
//    		session.setAttribute("phoneNumber", phoneNumber);
//    	}
//    	
//    	session.setAttribute("currentPage", page);
//    	
//        int totalRecord = productService.countSearch( productCode, productName );
//        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
//        List<ProductEntity> products = productService.searchProduct(productCode, productName, page);
//        model.addAttribute("isAdmin", productService.isAdmin());
//        model.addAttribute("productCode", productCode);
//        model.addAttribute("productName", productName);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("products", products);
//        model.addAttribute("totalPage",totalPage);
//        model.addAttribute("message", session.getAttribute("message"));
//        model.addAttribute("url","product");
//        session.removeAttribute("message");
//        return "product/product-list";
//    }
//
//  
//}
