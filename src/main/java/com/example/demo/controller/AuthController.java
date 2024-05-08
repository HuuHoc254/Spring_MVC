package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.security.UserDetailImpl;
import com.example.demo.service.IAccountService;

@Controller
@RequestMapping
public class AuthController {
	@Autowired
	private IAccountService accountService;

	 @GetMapping("/login")
	 public String showFormLogin(){
		 return "auth/login";
	  }

	 @GetMapping("/logout-action")
	 public String logout(Model model) {
		 UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			accountService.setOnline(userDetail.getId());
		 SecurityContextHolder.getContext().setAuthentication(null);
		 SecurityContextHolder.clearContext();
		 return "redirect:/login?logout=true";
	 }
	 @GetMapping("login/403")
	 public String showErrors403(Model model) {
		 return "auth/403";
	 }
}