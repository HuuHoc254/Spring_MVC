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

	public Map<String,String> validateInsertAccount(InsertAccount insertAccountRequest) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(insertAccountRequest.getAccountName() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
	        check = accountService.existsByAccountName(insertAccountRequest.getAccountName());
	        if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(insertAccountRequest.getPassword()== "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!insertAccountRequest.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }
        
        if(!insertAccountRequest.getConfirmPassword().equals(insertAccountRequest.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(insertAccountRequest.getFullName()== "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(insertAccountRequest.getPhoneNumber()== "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại!");
        } else {
	        check = accountService.existsByphoneNumber(insertAccountRequest.getPhoneNumber());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }
        
        return mapErrors;
    }
	
	public Map<String,String> validateUpdateAccount(UpdateAccount updateAccount) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(updateAccount.getAccountName() == "") {
        	mapErrors.put("accountName", "Không được để trống tên đăng nhập!");
        }else {
	        check = accountService.existsByAccountNameNotId(updateAccount.getAccountName(), updateAccount.getAccountId());
	        if ( check ) {
	            mapErrors.put("accountName", "Tên đăng nhập đã tồn tại!.");
	        }
        }

        if(updateAccount.getPassword()== "") {
        	mapErrors.put("password", "Không được để trống mật khẩu!");
        }else if(!updateAccount.getPassword().matches( "^(?=.*[A-Z])(?=.*\\d).+{8,}$" )){
        	mapErrors.put("password", "Sai định dạng mật khẩu!");
        }
        
        if(!updateAccount.getConfirmPassword().equals(updateAccount.getPassword())  ) {
        	mapErrors.put("confirmPassword", "Xác nhận mật khẩu không trùng khớp!");
        }

        if(updateAccount.getFullName()== "") {
        	mapErrors.put("fullName", "Không được để tên nhân viên!");
        }

        if(updateAccount.getPhoneNumber()== "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại!");
        } else {
	        check = accountService.existsByPhoneNumberNotId(updateAccount.getPhoneNumber(), updateAccount.getAccountId());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }
        
        return mapErrors;
    }

}