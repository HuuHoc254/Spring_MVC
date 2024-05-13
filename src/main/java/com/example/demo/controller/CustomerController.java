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

import com.example.demo.dto.CreateCustomer;
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
	final int LIMIT = 3;
	@Autowired
	private CustomerValidate validate;
	@Autowired
	private AuthService authService;
    @Autowired
    private ICustomerService customerService;
    private static final Logger logger = Logger.getLogger(CustomerController.class);

    @GetMapping
    private String search(HttpServletRequest request
						, Model model
						, @RequestParam(defaultValue = "") String name
						, @RequestParam(defaultValue = "") String phone
						, @RequestParam(defaultValue = "1") int page
						){
    	name = name.trim();
    	phone = phone.trim();

    	HttpSession session = request.getSession();
    	session.setAttribute("customerName", name);
    	session.setAttribute("phoneCustomer", phone);
    	session.setAttribute("currentPage", page);
    	
        int totalRecord = customerService.countSearch( name, phone);
        int totalPage = totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
        List<Customer> customers 
        					= customerService.search( name
													, phone
													, page);
        model.addAttribute("isAdmin", authService.isAdmin());
        model.addAttribute("name", name);
    	model.addAttribute("accountId", authService.getIdLogin());
        model.addAttribute("phone", phone);
        model.addAttribute("currentPage", page);
        model.addAttribute("customers", customers);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("successMessage", session.getAttribute("successMessage"));
        model.addAttribute("url","customer");
        session.removeAttribute("successMessage");
        return "customer/list";
    }

    @GetMapping("/cancel")
    private String cancel( HttpServletRequest request,  Model model){
    	HttpSession session = request.getSession();
		String name = (String) session.getAttribute("customerName");
    	String phone = (String) session.getAttribute("phoneCustomer");
    	int page = 1;
    	if(session.getAttribute("currentPage")!=null) {
    		page = (int) session.getAttribute("currentPage");
    	}
    	String url = "redirect:/customer?page="+page;
    	if( name.equals("") ) {
    		url += "&name="+name;
    	}
    	if(phone!="") {
    		url += "&phone="+phone;
    	}
		return url;
    }

    @GetMapping("/create")
    private String showFormCreate(Model model){
    	model.addAttribute("customer",new CreateCustomer());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","customer");
	    return "customer/create";
    }

	@PostMapping("/store")
    private String create(Model model, @ModelAttribute CreateCustomer customer){
		customer.setAccountId(authService.getIdLogin());
    	Map<String, String> mapErrors = validate.create(customer);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("customer", new CreateCustomer());
    		if(customerService.create(customer)) {
    			model.addAttribute("successMessage", "Thêm mới khách hàng thành công!");
    		};
    	} else {
    		model.addAttribute("customer",customer);
    	}
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","customer");
    	model.addAttribute("mapErrors",mapErrors);	
    	return "customer/create";
    }

	@GetMapping("/edit/{id}")
    private String showFormEdit(Model model
							,  	@PathVariable("id") int id){
		try {
			Customer customer = customerService.getById(id);
			if(authService.getIdLogin() != customer.getAccount().getId() && !authService.isAdmin()) {
	    		logger.info("---------------BEGIN-------------");
	    		logger.error("Account has ID= "+ authService.getIdLogin() + " request UPDATE customer HAS ID= " + id);
	    		logger.error("Account can't update this customer");
	    		logger.info("---------------END---------------");
	    		throw new RuntimeException("Bạn không có quyền chỉnh sửa khách hàng này!");
	    	}
	    	UpdateCustomer customerU = new UpdateCustomer();
	    	BeanUtils.copyProperties(customer,customerU);
	    	model.addAttribute("mapErrors",new HashMap<String, String>());
	    	model.addAttribute("customer", customerU);
	    	model.addAttribute("isAdmin", authService.isAdmin());
	    	model.addAttribute("url","customer");
		    return "customer/edit";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}

    }

	@PostMapping("/update/{customerId}")
    private String update( 	HttpServletRequest request
						, 	Model model
						, 	@ModelAttribute UpdateCustomer customer
						, 	@PathVariable int customerId
							){
		try {
			HttpSession session = request.getSession();
	    	String name = (String) session.getAttribute("customerName");
	    	String phone = (String) session.getAttribute("phoneCustomer");
	    	int page = 1;
	    	if(session.getAttribute("currentPage")!=null) {
	    		page = (int) session.getAttribute("currentPage");
	    	}

	    	String url = "redirect:/customer?page="+page;
	    	if( name.equals("") ) {
	    		url += "&name="+name;
	    	}
	    	if( phone.equals("") ) {
	    		url += "&phone="+phone;
	    	}

	    	Map<String, String> mapErrors = validate.update(customer);
			if( mapErrors.size()== 0 ) {
				if( customerService.update(customer)) {
					session.setAttribute("successMessage", "Chỉnh sửa khách hàng thành công!");
					return url;
				}else {
					throw new RuntimeException("Chỉnh sửa nhân viên không thành công!");
				}
			}
	    	model.addAttribute("customer",customer); 
	    	model.addAttribute("mapErrors",mapErrors);
	    	model.addAttribute("isAdmin", authService.isAdmin());
	    	model.addAttribute("url","customer");
	    	return "customer/edit";	
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    }

	@GetMapping("/destroy/{id}")
    private String delete( HttpServletRequest request
	   							, Model model
	   							, @PathVariable("id") int id){
		try {
			HttpSession session = request.getSession();
		   	Customer customer = customerService.getById(id);
		   	if(authService.getIdLogin()!= customer.getAccount().getId() && !authService.isAdmin()) {
				logger.info("---------------BEGIN-------------");
				logger.error("Account has ID= "+ authService.getIdLogin() + " request DELETE customer HAS ID= " + id);
				logger.error("Account can't delete this customer");
				logger.info("---------------END---------------");
				throw new RuntimeException("Xóa khách hàng thất bại vì bạn không có quyền!");
		   	} else {
			   	boolean check = customerService.delete(id);
			   	if (check) {
			   		session.setAttribute("successMessage", "Đã xóa khách hàng thành công!");
			   	}else {
			   		throw new RuntimeException("Xóa khách hàng thất bại, đã có lỗi!");
			   	}
		   	}
		   	int page = 1;
		   	if(session.getAttribute("currentPage")!=null) {
				page = (int) session.getAttribute("currentPage");
			} 
	   	
		   	String name = (String) session.getAttribute("accountName");
		   	String phone = (String) session.getAttribute("phoneCustomer");
		   	String url = "redirect:/customer?page="+page;
		   	if( name.equals("") ) {
		   		url += "&accountName="+name;
		   	}
		   	if( phone.equals("") ) {
		   		url += "&phone="+phone;
		   	}
			    return url;
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
	   	
   }
}
