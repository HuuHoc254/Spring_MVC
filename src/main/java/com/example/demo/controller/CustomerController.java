package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.InsertCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.model.Customer;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.CustomerValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerValidate validate;
	@Autowired
	private AuthService authService;
    @Autowired
    private ICustomerService customerService;
    private static final Logger logger = Logger.getLogger(CustomerController.class);

    @GetMapping
    private String searchCustomer(HttpServletRequest request
    							, Model model
    							, @RequestParam(defaultValue = "") String customerName
    							, @RequestParam(defaultValue = "") String phoneNumber
    							, @RequestParam(defaultValue = "1") int page
    							){
    	HttpSession session = request.getSession();
    	if( customerName != "") {
    		session.setAttribute("customerName", customerName);
    	}
    	if( phoneNumber != "") {
    		session.setAttribute("phoneNumberC", phoneNumber);
    	}
    	
    	session.setAttribute("currentPage", page);
    	
        int totalRecord = customerService.countSearch( customerName, phoneNumber);
        int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
        List<Customer> customers 
        					= customerService.search( customerName
													, phoneNumber
													, page);
        model.addAttribute("isAdmin", authService.isAdmin());
        model.addAttribute("customerName", customerName);
    	model.addAttribute("accountId", authService.getIdLogin());
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("currentPage", page);
        model.addAttribute("customers", customers);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("message", session.getAttribute("message"));
        model.addAttribute("url","customer");
        session.removeAttribute("message");
        return "customer/customer-list";
    }

    @GetMapping("/cancel")
    private String cancel( HttpServletRequest request,  Model model){
    	HttpSession session = request.getSession();
		String customerName = (String) session.getAttribute("customerName");
    	String phoneNumber = (String) session.getAttribute("phoneNumberC");
    	int page = 1;
    	if(session.getAttribute("currentPage")!=null) {
    		page = (int) session.getAttribute("currentPage");
    	}
    	String search = "redirect:/customer?page="+page;
    	if(customerName!="") {
    		search += "&customerName="+customerName;
    	}
    	if(phoneNumber!="") {
    		search += "&phoneNumber="+phoneNumber;
    	}
		return search;
    }

    @GetMapping("/insert")
    private String showFormInsert(Model model){
    	model.addAttribute("insertCustomer",new InsertCustomer());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "customer/customer-add";
    }

	@PostMapping("/insert")
    private String insertCustomer(Model model, @ModelAttribute InsertCustomer customer){
		customer.setAccountId(authService.getIdLogin());
    	Map<String, String> mapErrors = validate.create(customer);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("insertCustomer", new InsertCustomer());
    		if(customerService.create(customer)) {
    			model.addAttribute("message", "Thêm mới sản phẩm thành công!");
    		};
    	} else {
    		model.addAttribute("insertCustomer",customer);
    	}
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("mapErrors",mapErrors);	
    	return "customer/customer-add";
    }

	@GetMapping("/update/{customerId}")
    private String showFormUpdate( HttpServletRequest request
								,  Model model
								,  @PathVariable("customerId") int customerId){
		HttpSession session = request.getSession();
		Customer customer = customerService.getById(customerId);
		if(customer==null) {
			String customerName = (String) session.getAttribute("customerName");
	    	String phoneNumber = (String) session.getAttribute("phoneNumberC");
	    	int page = 1;
	    	if(session.getAttribute("currentPage")!=null) {
	    		page = (int) session.getAttribute("currentPage");
	    	}

	    	String search = "redirect:/customer?page="+page;
	    	if(customerName!="") {
	    		search += "&customerName="+customerName;
	    	}
	    	if(phoneNumber!="") {
	    		search += "&phoneNumber="+phoneNumber;
	    	}
    		session.setAttribute("message", "Khách hàng không tồn tại!");
    		logger.info("---------------BEGIN-------------");
    		logger.error("Account has ID= "+ authService.getIdLogin() + " request UPDATE customer HAS ID= "+customerId);
    		logger.error("Customer not exits");
    		logger.info("---------------END---------------");
    		return search;
		}
		if(authService.getIdLogin()!= customer.getAccount().getAccountId() && !authService.isAdmin()) {
			String customerName = (String) session.getAttribute("customerName");
	    	String phoneNumber = (String) session.getAttribute("phoneNumberC");
	    	int page = 1;
	    	if(session.getAttribute("currentPage")!=null) {
	    		page = (int) session.getAttribute("currentPage");
	    	}

	    	String search = "redirect:/customer?page="+page;
	    	if(customerName!="") {
	    		search += "&customerName="+customerName;
	    	}
	    	if(phoneNumber!="") {
	    		search += "&phoneNumber="+phoneNumber;
	    	}
    		session.setAttribute("message", "Bạn không có quyền chỉnh sửa khách hàng này!");
    		logger.info("---------------BEGIN-------------");
    		logger.error("Account has ID= "+ authService.getIdLogin() + " request UPDATE customer HAS ID= "+customerId);
    		logger.error("Account can't update this customer");
    		logger.info("---------------END---------------");
    		return search;
    	}
    	UpdateCustomer uCustomer = new UpdateCustomer();
    	BeanUtils.copyProperties(customer,uCustomer);
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("updateCustomer", uCustomer);
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "customer/customer-update";
    }

	@PostMapping("/update/{customerId}")
    private String updateCustomer( HttpServletRequest request
    							, Model model
    							, @ModelAttribute UpdateCustomer customer
    							, @PathVariable int customerId
    							){
    	HttpSession session = request.getSession();
    	String customerName = (String) session.getAttribute("customerName");
    	String phoneNumber = (String) session.getAttribute("phoneNumberC");
    	int page = 1;
    	if(session.getAttribute("currentPage")!=null) {
    		page = (int) session.getAttribute("currentPage");
    	}

    	String search = "redirect:/customer?page="+page;
    	if(customerName!="") {
    		search += "&customerName="+customerName;
    	}
    	if(phoneNumber!="") {
    		search += "&phoneNumber="+phoneNumber;
    	}

    	Map<String, String> mapErrors = validate.update(customer);
		if( mapErrors.size() == 0 && customerService.update(customer)) {
			session.setAttribute("message", "Chỉnh sửa khách hàng thành công!");
			return search;
		}
    	model.addAttribute("updateCustomer",customer); 
    	model.addAttribute("mapErrors",mapErrors);
    	model.addAttribute("isAdmin", authService.isAdmin());
    	return "customer/customer-update";	
    }

	@GetMapping("/delete/{customerId}")
    private String deleteAccount( HttpServletRequest request
   							, Model model
   							, @PathVariable("customerId") int id){
   	HttpSession session = request.getSession();
   	Customer customer = customerService.getById(id);
	if(authService.getIdLogin()!= customer.getAccount().getAccountId() && !authService.isAdmin()) {
		session.setAttribute("message", "Xóa khách hàng thất bại vì bạn không có quyền!");
		logger.info("---------------BEGIN-------------");
		logger.error("Account has ID= "+ authService.getIdLogin() + " request DELETE customer HAS ID= " + id);
		logger.error("Account can't delete this customer");
		logger.info("---------------END---------------");
	} else {
	   	boolean check = customerService.delete(id);
	   	if (check) {
	   		session.setAttribute("message", "Đã xóa khách hàng thành công!");
	   	}else {
	   		session.setAttribute("message", "Xóa khách hàng thấSt bại, đã có lỗi sảy ra!");
	   	}
   	}
   	int page = 1;
   	if(session.getAttribute("currentPage")!=null) {
		page = (int) session.getAttribute("currentPage");
	} 
   	
   	String accountName = (String) session.getAttribute("accountName");
   	String phoneNumber = (String) session.getAttribute("phoneNumberC");
   	String search = "redirect:/customer?page="+page;
   	if(accountName!="") {
   		search += "&accountName="+accountName;
   	}
   	if(phoneNumber!="") {
   		search += "&phoneNumber="+phoneNumber;
   	}
	    return search;
   }
}
