package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AccountEntity;
import com.example.demo.service.IAccountService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
    @Autowired
    private IAccountService accountService;
    @Override
    @Transactional
        public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        AccountEntity account =  accountService.findByAccountName(accountName);
        if (account==null) { 
        	new UsernameNotFoundException("Account Not Found with accountName: " + accountName);
    	}
        return UserDetailImpl.convertAccountEntityToAccountDetail(account);
    }
}