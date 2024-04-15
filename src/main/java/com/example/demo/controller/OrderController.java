package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.impl.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private AuthService authService;

    @GetMapping
    private String searchProduct(HttpServletRequest request
    							, Model model
    							, @RequestParam(defaultValue = "") String customerName
    							, @RequestParam(defaultValue = "") String phoneNumber
    							, @RequestParam(defaultValue = "1") int page
    							){
//    	HttpSession session = request.getSession();
//    	if( customerName != "") {
//    		session.setAttribute("customerName", customerName);
//    	}
//    	if( phoneNumber != "") {
//    		session.setAttribute("phoneNumberC", phoneNumber);
//    	}
//    	
//    	session.setAttribute("currentPage", page);
//    	
//        int totalRecord = customerService.countSearch( customerName, phoneNumber);
//        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
//        List<Customer> customers 
//        					= customerService.searchCustomer( customerName
//        													, phoneNumber
//        													, page);
//        model.addAttribute("isAdmin", authService.isAdmin());
//        model.addAttribute("productCode", customerName);
//    	model.addAttribute("accountId", authService.getIdLogin());
//        model.addAttribute("productName", phoneNumber);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("customers", customers);
//        model.addAttribute("totalPage",totalPage);
//        model.addAttribute("message", session.getAttribute("message"));
//        model.addAttribute("url","customer");
//        session.removeAttribute("message");
        return "order/order";
    }

}
