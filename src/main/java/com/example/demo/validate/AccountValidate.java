package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.InsertAccount;
import com.example.demo.dto.UpdateAccount;
import com.example.demo.service.IAccountService;

@Component
public class AccountValidate {

	@Autowired
    private IAccountService accountService;

	public Map<String,String> create(InsertAccount create) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(create.getAccountName().trim() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
	        check = accountService.existsByName(create.getAccountName());
	        if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(create.getPassword().trim() == "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!create.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }

        if(!create.getConfirmPassword().equals(create.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(create.getFullName().trim() == "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(create.getPhoneNumber()== "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại!");
        } else {
	        check = accountService.existsByPhone(create.getPhoneNumber());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

	public Map<String,String> update(UpdateAccount update) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(update.getAccountName().trim() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
        	check = accountService.existsByNameNotId(update.getAccountName(), update.getAccountId());
        	if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(update.getPassword().trim() == "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!update.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }

        if(!update.getConfirmPassword().equals(update.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(update.getFullName().trim() == "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(update.getPhoneNumber().trim() == "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại!");
        } else {
	        check = accountService.existsByPhoneNotId(update.getPhoneNumber(), update.getAccountId());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

}