package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Allocation;
import com.example.demo.dto.AllocationList;
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
	@Autowired
	private OrderValidate validate;
    @GetMapping("/order")
    private String searchOrder(HttpServletRequest request
							, Model model
							, @RequestParam(defaultValue = "") String accountName
							, @RequestParam(defaultValue = "") String fullName
							, @RequestParam(defaultValue = "") String productCode
							, @RequestParam(defaultValue = "") String productName
							, @RequestParam(defaultValue = "") String customerName
							, @RequestParam(defaultValue = "") String customerPhone
							, @RequestParam(defaultValue = "") String beginOrderDate
							, @RequestParam(defaultValue = "") String endOrderDate
							, @RequestParam(defaultValue = "") String orderStatus
							, @RequestParam(defaultValue = "") String allocationStatus
							, @RequestParam(defaultValue = "1") int page
    							){
    	HttpSession session = request.getSession();
    	session.setAttribute("currentPage", page);
    	boolean checkBeginDate = false;
    	boolean checkEndDate = false;
    	try {
    		if(!beginOrderDate.isEmpty()) {
    			LocalDate.parse(beginOrderDate);
    		}
    		checkBeginDate = true;
    		if(!endOrderDate.isEmpty()) {
    			LocalDate.parse(endOrderDate);
    		}
    		checkEndDate = true;
		} catch (Exception e) {
			if(checkBeginDate) {
				checkEndDate = false;
			}
		} 
        int totalRecord = orderService.countSearch(accountName
												,  fullName
												,  productCode
												,  productName
												,  customerName
												,  customerPhone
												,  checkBeginDate ? beginOrderDate : "9999-12-31"
												,  checkEndDate ? endOrderDate : "9999-12-31"
												,  orderStatus
												,  allocationStatus
												,  authService.getIdLogin()
												,  authService.isAdmin()
													);
        Map<Integer, Map<String,String>> mapErrors = null;
        
        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
        List<Order> orders 
        					= orderService.search( accountName
												,  fullName
												,  productCode
												,  productName
												,  customerName
												,  customerPhone
												,  checkBeginDate ? beginOrderDate : "9999-12-31"
												,  checkEndDate ? endOrderDate : "9999-12-31"
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
        model.addAttribute("customerPhone", customerPhone);
        model.addAttribute("beginOrderDate", beginOrderDate);
        model.addAttribute("endOrderDate", endOrderDate);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("allocationStatus", allocationStatus);
        model.addAttribute("mapErrors", mapErrors);
        model.addAttribute("currentPage", page);
        model.addAttribute("orders", orders);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("message", session.getAttribute("message"));
        model.addAttribute("url","order");
        session.removeAttribute("message");
        session.removeAttribute("mapErrors");
        return "order/list";
    }

	@GetMapping("/admin/allocation")
    private String showForm( HttpServletRequest request
						   , Model model
							){
		model.addAttribute("allocaties", new AllocationList());
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","allocation");
	    return "allocation/allocation";
    }
	@PostMapping("/admin/allocation")
    private String allocation( HttpServletRequest request
							,  Model model
							,  @ModelAttribute AllocationList allocationList){

		Map<Integer, Map<String, String>> mapErrors = validate.allocation(allocationList.getAllocaties());
		if(mapErrors.size() >0) {
			model.addAttribute("mapErrors", mapErrors);
		}
		model.addAttribute("allocaties", allocationList);
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","allocation");
	    return "allocation/allocation";
    }

}
