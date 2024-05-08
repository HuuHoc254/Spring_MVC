package com.example.demo.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.security.UserDetailImpl;

@Service
public class AuthService{
	private final String ADMIN = "ROLE_ADMIN";
	public boolean isAdmin() {
		UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getAuthorities().toString().contains(ADMIN);
	}
	public int getIdLogin() {
		UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getId(); 
	}
}