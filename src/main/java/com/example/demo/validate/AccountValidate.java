package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CreateAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.service.IAccountService;

@Component
public class AccountValidate {

	@Autowired
    private IAccountService accountService;

	public Map<String,String> create(CreateAccount account) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(account.getName() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
	        check = accountService.existsByName(account.getName());
	        if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(account.getPassword() == "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!account.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }

        if(!account.getConfirmPassword().equals(account.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(account.getFullName() == "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(account.getPhone()== "") {
        	mapErrors.put("phone", "Không được để trống số điện thoại!");
        } else if(!account.getPhone().matches( "^0\\d{9}$" )){
        	mapErrors.put("phone", "Sai định dạng số điện thoại!");
        }else {
	        check = accountService.existsByPhone(account.getPhone());
	        if ( check ) {
	            mapErrors.put("phone", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

	public Map<String,String> update(UpdateAccount account) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(account.getName() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
        	check = accountService.existsByNameNotId(account.getName(), account.getId());
        	if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(account.getPassword() == "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!account.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }

        if(!account.getConfirmPassword().equals(account.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(account.getFullName() == "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(account.getPhone() == "") {
        	mapErrors.put("phone", "Không được để trống số điện thoại!");
        } else if(!account.getPhone().matches( "^0\\d{9}$" )){
        	mapErrors.put("phone", "Sai định dạng số điện thoại!");
        } else {
	        check = accountService.existsByPhoneNotId(account.getPhone(), account.getId());
	        if ( check ) {
	            mapErrors.put("phone", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

}