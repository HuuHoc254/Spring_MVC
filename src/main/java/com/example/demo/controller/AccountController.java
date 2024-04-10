package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.AccountEntity;
import com.example.demo.security.UserDetailImpl;
import com.example.demo.service.IAccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class AccountController {
	private final String ADMIN = "ROLE_ADMIN";

	@Autowired
	private IAccountService accountService;

	@GetMapping("account/loginSuccess")
	public String loginSuccess(Model model) {
		UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		accountService.setOnline(userDetail.getAccountId());
		
	return "redirect:/product";
	}
	@GetMapping("admin/account")
	private String searchProduct( HttpServletRequest request
								, Model model
								, @RequestParam(defaultValue = "") String accountName
								, @RequestParam(defaultValue = "") String fullName
								, @RequestParam(defaultValue = "") String phoneNumber
								, @RequestParam(defaultValue = "1") int page
			){
		UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
		List<AccountEntity> accounts = accountService.searchAccount(accountName, fullName, phoneNumber, page);
		model.addAttribute("isAdmin", userDetail.getAuthorities().toString().contains(ADMIN));
		model.addAttribute("accountName", accountName);
		model.addAttribute("fullName", fullName);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("currentPage", page);
		model.addAttribute("accounts", accounts);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("url","account");
		model.addAttribute("message", session.getAttribute("message"));
		session.removeAttribute("message");
		return "account-list";
	}
}