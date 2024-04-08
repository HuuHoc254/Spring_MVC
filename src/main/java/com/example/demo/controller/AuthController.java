package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.request.LoginRequest;

@Controller
@RequestMapping
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	 @GetMapping("login")
	 public String showFormLogin(){
		 return "login";
	  }
//	 @PostMapping("login")
//	 public String login(Model model, @ModelAttribute LoginRequest request) {
//		String accountName = request.getAccountName();
//        String password = request.getPassword();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(accountName, password);
//        
//        try {
//            // Thực hiện xác thực thông tin đăng nhập
//            authenticationManager.authenticate(authentication);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//            return "redirect:/product";
//        } catch (AuthenticationException e) {
//            // Xử lý ngoại lệ khi đăng nhập không thành công
//            return "";
//        }
//	 }
	 
	 @GetMapping("logout")
	 public String logout(Model model) {
		 SecurityContextHolder.getContext().setAuthentication(null);
		 SecurityContextHolder.clearContext();
		 return "redirect:/login?logout=true";
	 }
}