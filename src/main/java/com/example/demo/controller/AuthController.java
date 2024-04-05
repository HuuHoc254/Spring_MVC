package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
	 @GetMapping("login")
	 public String showFormLogin(){
		 return "login";
	  }
	 @PostMapping
	 public String login(Model model) {
		 return "";
	 }
}