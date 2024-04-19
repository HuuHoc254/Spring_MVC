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

import com.example.demo.dto.InsertAccount;
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
	private String searchAccount( HttpServletRequest request
								, Model model
								, @RequestParam(defaultValue = "") String accountName
								, @RequestParam(defaultValue = "") String fullName
								, @RequestParam(defaultValue = "") String phoneNumber
								, @RequestParam(defaultValue = "1") int page
			){
		HttpSession session = request.getSession();
		if( accountName != "") {
			session.setAttribute("accountName", accountName);
		}
		if( fullName != "") {
			session.setAttribute("fullName", fullName);
		}
		if( phoneNumber != "") {
			session.setAttribute("phoneNumber", phoneNumber);
		}

		session.setAttribute("currentPage", page);

		int totalRecord = accountService.countSearch( accountName, fullName, phoneNumber);
		int totalPage = totalRecord % 3 == 0 ? totalRecord / 3 : totalRecord / 3 + 1;
		List<Account> accounts = accountService.searchAccount(accountName, fullName, phoneNumber, page);
		model.addAttribute("isAdmin", authService.isAdmin());
		model.addAttribute("accountName", accountName);
		model.addAttribute("fullName", fullName);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("currentPage", page);
		model.addAttribute("accounts", accounts);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("url","account");
		model.addAttribute("message", session.getAttribute("message"));
		session.removeAttribute("message");
		return "account/account-list";
	}
	
	@PostMapping("/account/update/{accountId}")
    private String updateAccount( HttpServletRequest request
    							, Model model
    							, @ModelAttribute UpdateAccount updateAccount
    							, @PathVariable int accountId
    							){
    	HttpSession session = request.getSession();
    	String accountName = (String) session.getAttribute("accountName");
    	String fullName = (String) session.getAttribute("fullName");
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	int page = (int) session.getAttribute("currentPage");
    	String search = "redirect:/admin/account?page="+page;
    	if(accountName!="") {
    		search += "&accountName="+accountName;
    	}
    	if(fullName!="") {
    		search += "&fullName="+fullName;
    	}
    	if(phoneNumber!="") {
    		search += "&phoneNumber="+phoneNumber;
    	}
    	
    	Map<String, String> mapErrors = validate.validateUpdateAccount(updateAccount);
		if( mapErrors.size() == 0 && accountService.updateAccount(updateAccount)) {
			session.setAttribute("message", "Cập nhật sản phẩm thành công!");
			return search;
		}
    	model.addAttribute("updateAccount",updateAccount); 
    	model.addAttribute("mapErrors",mapErrors);
    	model.addAttribute("isAdmin", authService.isAdmin());
    	return "account/account-update";	
    }

	@GetMapping("/account/insert")
    private String showFormInsert(Model model){
    	model.addAttribute("insertAccount",new InsertAccount());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "account/account-add";
    }

	@PostMapping("/account/insert")
    private String insertAccount(Model model, @ModelAttribute InsertAccount insertAccount){
    	Map<String, String> mapErrors = validate.validateInsertAccount(insertAccount);
    	if(mapErrors.size() == 0) {
    		model.addAttribute("insertAccount", new InsertAccount());
    		if(accountService.insertAccount(insertAccount)) {
    			model.addAttribute("message", "Thêm mới sản phẩm thành công!");
    		};
    	} else {
    		model.addAttribute("insertAccount",insertAccount);
    	}
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("mapErrors",mapErrors);	
    	return "account/account-add";

    }

	@GetMapping("/account/update/{accountId}")
    private String showFormUpdate(Model model, @PathVariable("accountId") int accountId){
    	Account account = accountService.getAccountById(accountId);
    	UpdateAccount uAccount = new UpdateAccount();
    	BeanUtils.copyProperties(account,uAccount);
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("updateAccount", uAccount);
    	model.addAttribute("isAdmin", authService.isAdmin());
	    return "account/account-update";
    }

	@GetMapping("/account/cancel")
    private String cancel( HttpServletRequest request, Model model){
    	HttpSession session = request.getSession();
    	String accountName = (String) session.getAttribute("accountName");
    	String fullName = (String) session.getAttribute("fullName");
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	int page = (int) session.getAttribute("currentPage");
    	String search = "redirect:/admin/account?page="+page;
    	if(accountName!="") {
    		search += "&accountName="+accountName;
    	}
    	if(fullName!="") {
    		search += "&fullName="+fullName;
    	}
    	if(phoneNumber!="") {
    		search += "&phoneNumber="+phoneNumber;
    	}
		return search;
    }

	 @GetMapping("/account/delete/{accountId}")
     private String deleteAccount( HttpServletRequest request
    							, Model model
    							, @PathVariable("accountId") int accountId){
    	HttpSession session = request.getSession();
    	boolean check = accountService.deleteAccount(accountId);
    	if (check) {
    		session.setAttribute("message", "Đã xóa nhân viên thành công!");
    	}else {
    		session.setAttribute("message", "Xóa nhân viên thất bại, đã có lỗi s!");
    	}
    	String accountName = (String) session.getAttribute("accountName");
    	String fullName = (String) session.getAttribute("fullName");
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	int page = (int) session.getAttribute("currentPage");
    	String search = "redirect:/admin/account?page="+page;
    	if(accountName!="") {
    		search += "&accountName="+accountName;
    	}
    	if(fullName!="") {
    		search += "&fullName="+fullName;
    	}
    	if(phoneNumber!="") {
    		search += "&phoneNumber="+phoneNumber;
    	}
	    return search;
    }
}