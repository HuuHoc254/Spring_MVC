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
import com.example.demo.dto.ProductBestSellerResponse;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.service.IOrderService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.OrderValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class OrderController {
	final int LIMIT = 3;
	@Autowired
	private AuthService authService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private OrderValidate validate;
    @GetMapping("/order")
    private String searchOrder(	HttpServletRequest request
							, 	Model model
							, 	@RequestParam(defaultValue = "") String accountName
							, 	@RequestParam(defaultValue = "") String fullName
							, 	@RequestParam(defaultValue = "") String productCode
							, 	@RequestParam(defaultValue = "") String productName
							, 	@RequestParam(defaultValue = "") String customerName
							, 	@RequestParam(defaultValue = "") String customerPhone
							, 	@RequestParam(defaultValue = "") String beginOrderDate
							, 	@RequestParam(defaultValue = "") String endOrderDate
							, 	@RequestParam(defaultValue = "") String orderStatus
							, 	@RequestParam(defaultValue = "") String allocationStatus
							, 	@RequestParam(defaultValue = "1") int page
    							){
    	accountName = accountName.trim();
    	fullName = fullName.trim();
    	productCode = productCode.trim();
    	productName = productName.trim();
    	customerName = customerName.trim();
    	customerPhone = customerPhone.trim();

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
        
        int totalPage = totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
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
        model.addAttribute("successMessage", session.getAttribute("successMessage"));
        model.addAttribute("url","order");
        session.removeAttribute("successMessage");
        session.removeAttribute("mapErrors");
        return "order/list";
    }

	@GetMapping("/admin/allocation")
    private String showForm( HttpServletRequest request
						   , Model model
							){
		model.addAttribute("allocationList", new AllocationList());
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","allocation");
	    return "allocation/allocation";
    }

	@PostMapping("/admin/allocation")
    private String allocation( HttpServletRequest request
							,  Model model
							,  @ModelAttribute("allocationList") AllocationList allocationList
								){
		Map<Integer, Map<String, String>> mapErrors = validate.allocation(allocationList.getAllocaties());
		if(mapErrors.size() > 0) {
			model.addAttribute("mapErrors", mapErrors);
		}else {
			orderService.allocate(allocationList.getAllocaties());
			HttpSession session = request.getSession();
			session.setAttribute("successMessage", "Phân bổ thành công!");
			return "redirect:/order";
		}
		model.addAttribute("allocationList", allocationList);
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","allocation");
	    return "allocation/allocation";
    }

	@GetMapping("/admin/report")
	private String searchAnalytics(	Model model
								, 	@RequestParam(defaultValue = "") 	String 	beginDate
								, 	@RequestParam(defaultValue = "") 	String 	endDate
								,	@RequestParam(defaultValue = "1") 	int		pageCustomer
								,	@RequestParam(defaultValue = "1") 	int		pageProductBestSeller
								,	@RequestParam(defaultValue = "1") 	int		pageProduct
								, 	HttpServletRequest request
									){
		
		
		
		List<Customer> customers = orderService.customerZeroOrder(beginDate, endDate, (pageCustomer-1)*LIMIT);
		List<ProductBestSellerResponse> productBestSeller = orderService.productBestSeller(beginDate, endDate, (pageProductBestSeller-1)*LIMIT);
		List<Product> products = orderService.productZeroOrder(beginDate, endDate, (pageProduct-1)*LIMIT);
		int totalRecord = orderService.totalCustomerZeroOrder(beginDate, endDate);
		int totalCustomerZeroOrder = totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
		totalRecord = orderService.totalProductBestSeller(beginDate, endDate);
		int totalProductBestSeller =  totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
		totalRecord = orderService.totalProductZeroOrder(beginDate, endDate);
		int totalProductZeroOrder =  totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
		
		model.addAttribute("customers", customers);
		model.addAttribute("productBestSeller", productBestSeller);
		model.addAttribute("products", products);
		model.addAttribute("totalCustomerZeroOrder", totalCustomerZeroOrder);
		model.addAttribute("totalProductBestSeller", totalProductBestSeller);
		model.addAttribute("totalProductZeroOrder", totalProductZeroOrder);
		model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","report");
    	model.addAttribute("beginDate",beginDate);
    	model.addAttribute("endDate",endDate);
    	model.addAttribute("pageCustomer",pageCustomer);
    	model.addAttribute("pageProductBestSeller",pageProductBestSeller);
    	model.addAttribute("pageProduct",pageProduct);
		return "analytics/show";
	}
}