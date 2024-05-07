package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.dto.CreateAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.model.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.AccountValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private AuthService authService;
	@Autowired
	private AccountValidate validate;

	@GetMapping("/account")
	private String search(HttpServletRequest request
						, Model model
						, @RequestParam(defaultValue = "") String name
						, @RequestParam(defaultValue = "") String fullName
						, @RequestParam(defaultValue = "") String phone
						, @RequestParam(defaultValue = "1") int page
							){
		try {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("fullName", fullName);
			session.setAttribute("phone", phone);
			session.setAttribute("currentPage", page);

			int totalRecord = accountService.countSearch( name, fullName, phone);
			int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
			List<Account> accounts = accountService.search(name, fullName, phone, page);
			model.addAttribute("isAdmin", authService.isAdmin());
			model.addAttribute("name", name);
			model.addAttribute("fullName", fullName);
			model.addAttribute("phone", phone);
			model.addAttribute("currentPage", page);
			model.addAttribute("accounts", accounts);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("url","account");
			model.addAttribute("successMessage", session.getAttribute("successMessage"));
			session.removeAttribute("successMessage");
			return "account/list";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
	}

	@PostMapping("/account/update/{id}")
    private String update(HttpServletRequest request
						, Model model
						, @ModelAttribute UpdateAccount account
						, @PathVariable int id
						){
		try {
			HttpSession session = request.getSession();
	    	String name = (String) session.getAttribute("accountName");
	    	String fullName = (String) session.getAttribute("fullName");
	    	String phone = (String) session.getAttribute("phone");
	    	int page = (int) session.getAttribute("currentPage");
	    	String url = "redirect:/admin/account?page="+page;
	    	if( !name.trim().equals("") ) {
	    		url += "&name="+name;
	    	}
	    	if( !fullName.trim().equals("") ) {
	    		url += "&fullName="+fullName;
	    	}
	    	if( !phone.trim().equals("") ) {
	    		url += "&phone="+phone;
	    	}
	    	
	    	Map<String, String> mapErrors = validate.update(account);
			if( mapErrors.size() == 0 && accountService.update(account)) {
				session.setAttribute("successMessage", "Cập nhật sản phẩm thành công!");
				return url;
			}
	    	model.addAttribute("account",account); 
	    	model.addAttribute("mapErrors",mapErrors);
	    	model.addAttribute("isAdmin", authService.isAdmin());
	    	return "account/edit";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    }

	@GetMapping("/account/create")
    private String showFormCreate(Model model){
    	model.addAttribute("account",new CreateAccount());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "account/create";
    }

	@PostMapping("/account/store")
    private String create(Model model, @ModelAttribute CreateAccount account){
		try {
			Map<String, String> mapErrors = validate.create(account);
	    	if(mapErrors.size() == 0) {
	    		model.addAttribute("account", new CreateAccount());
	    		if(accountService.create(account)) {
	    			model.addAttribute("successMessage", "Thêm mới sản phẩm thành công!");
	    		};
	    	} else {
	    		model.addAttribute("account",account);
	    	}
	    	model.addAttribute("isAdmin", authService.isAdmin());
	    	model.addAttribute("mapErrors",mapErrors);	
	    	return "account/create";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    	

    }

	@GetMapping("/account/edit/{id}")
    private String showFormUpdate(Model model, @PathVariable("id") int id){
    	Account account = accountService.getById(id);
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("account", account);
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "account/edit";
    }

	@GetMapping("/account/cancel")
    private String cancel( HttpServletRequest request, Model model){
    	HttpSession session = request.getSession();
    	String name = (String) session.getAttribute("accountName");
    	String fullName = (String) session.getAttribute("fullName");
    	String phone = (String) session.getAttribute("phone");
    	int page = 1;
    	if( session.getAttribute("currentPage")!=null ) {
    		page = (int) session.getAttribute("currentPage");
    	}
    	String url = "redirect:/admin/account?page="+page;
    	if( !name.trim().equals("") ) {
    		url += "&name="+name;
    	}
    	if( !fullName.trim().equals("") ) {
    		url += "&fullName="+fullName;
    	}
    	if( !phone.trim().equals("") ) {
    		url += "&phone="+phone;
    	}
		return url;
    }

	 @GetMapping("/account/destroy/{id}")
     private String deleteAccount( HttpServletRequest request
    							, Model model
    							, @PathVariable("id") int id){
    	try {
    		HttpSession session = request.getSession();
        	boolean check = accountService.delete(id);
        	if (check) {
        		session.setAttribute("successMessage", "Đã xóa nhân viên thành công!");
        	}else {
        		throw new RuntimeException("Nhân viên không tồn tại!");
        	}
        	String name = (String) session.getAttribute("accountName");
        	String fullName = (String) session.getAttribute("fullName");
        	String phone = (String) session.getAttribute("phone");
        	int page = (int) session.getAttribute("currentPage");
        	String url = "redirect:/admin/account?page="+page;
        	if( !name.trim().equals("") ) {
        		url += "&name="+name;
        	}
        	if( !fullName.trim().equals("") ) {
        		url += "&fullName="+fullName;
        	}
        	if( !phone.trim().equals("") ) {
        		url += "&phone="+phone;
        	}
    	    return url;
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    }
}