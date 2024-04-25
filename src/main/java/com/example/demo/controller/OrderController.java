package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.SaveOrder;
import com.example.demo.model.Order;
import com.example.demo.service.IOrderService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.OrderValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class OrderController {
	@Autowired
	private AuthService authService;
	@Autowired
	private IOrderService orderService;

    @GetMapping("/order")
    private String searchProduct(HttpServletRequest request
    							, Model model
    							, @RequestParam(defaultValue = "") String accountName
    							, @RequestParam(defaultValue = "") String fullName
    							, @RequestParam(defaultValue = "") String productCode
    							, @RequestParam(defaultValue = "") String productName
    							, @RequestParam(defaultValue = "") String customerName
    							, @RequestParam(defaultValue = "") String phoneNumberCustomer
    							, @RequestParam(defaultValue = "") String beginOrderDate
    							, @RequestParam(defaultValue = "") String endOrderDate
    							, @RequestParam(defaultValue = "") String orderStatus
    							, @RequestParam(defaultValue = "") String allocationStatus
    							, @RequestParam(defaultValue = "1") int page
    							){
    	HttpSession session = request.getSession();
//    	if( accountName != "") {
//    		session.setAttribute("customerNameO", customerName);
//    	}
//    	if( phoneNumber != "") {
//    		session.setAttribute("phoneNumberC", phoneNumber);
//    	}
//    	
    	session.setAttribute("currentPage", page);
    	
        int totalRecord = orderService.countSearch(accountName
												,  fullName
												,  productCode
												,  productName
												,  customerName
												,  phoneNumberCustomer
												,  beginOrderDate
												,  endOrderDate
												,  orderStatus
												,  allocationStatus
												,  authService.getIdLogin()
												,  authService.isAdmin()
													);
        Map<Integer, Map<String,String>> mapErrors = null;
        
        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
        List<Order> orders 
        					= orderService.searchCustomer( accountName
														,  fullName
														,  productCode
														,  productName
														,  customerName
														,  phoneNumberCustomer
														,  beginOrderDate
														,  endOrderDate
														,  orderStatus
														,  allocationStatus
														,  authService.getIdLogin()
														,  authService.isAdmin()
														,  page
										);
        model.addAttribute("isAdmin", authService.isAdmin());
        model.addAttribute("accountName", accountName);
        model.addAttribute("fullName", fullName);
        model.addAttribute("productCode", productCode);
        model.addAttribute("productName", productName);
        model.addAttribute("customerName", customerName);
        model.addAttribute("phoneNumberCustomer", phoneNumberCustomer);
        model.addAttribute("beginOrderDate", beginOrderDate);
        model.addAttribute("endOrderDate", endOrderDate);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("allocationStatus", allocationStatus);
        model.addAttribute("mapErrors", mapErrors);
        session.removeAttribute("mapErrors");
        
//        session.setAttribute("accountNameO", accountName);
//        session.setAttribute("fullNameO", fullName);
//        session.setAttribute("productCodeO", productCode);
//        session.setAttribute("productNameO", productName);
//        session.setAttribute("customerNameO", customerName);
//        session.setAttribute("phoneNumberCustomerO", phoneNumberCustomer);
//        session.setAttribute("beginOrderDateO", beginOrderDate);
//        session.setAttribute("endOrderDateO", endOrderDate);
//        session.setAttribute("orderStatusO", orderStatus);
//        session.setAttribute("allocationStatusO", allocationStatus);
//        session.setAttribute("currentPage", page);

        model.addAttribute("currentPage", page);
        model.addAttribute("orders", orders);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("message", session.getAttribute("message"));
        model.addAttribute("url","order");
        session.removeAttribute("message");
        return "order/order";
    }

	@GetMapping("/admin/allocation")
    private String showFormUpdate( HttpServletRequest request
								,  Model model
								){
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","allocation");
	    return "allocation/allocation";
    }
	
}
