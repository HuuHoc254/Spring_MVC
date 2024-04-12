package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.InsertCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.service.ICustomerService;

@Component
public class CustomerValidate {

	@Autowired
    private ICustomerService customerService;

	public Map<String,String> validateInsertCustomer(InsertCustomer insertCustomer) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(insertCustomer.getCustomerName() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(insertCustomer.getAddress()== "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(insertCustomer.getPhoneNumber()== "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else {
	        check = customerService.existsByPhoneNumber(insertCustomer.getPhoneNumber());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }
        
        return mapErrors;
    }

	public Map<String,String> validateUpdateCustomer(UpdateCustomer updateCustomer) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(updateCustomer.getCustomerName() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(updateCustomer.getAddress()== "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(updateCustomer.getPhoneNumber()== "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else {
	        check = customerService.existsByPhoneNumberNotId(updateCustomer.getPhoneNumber(), updateCustomer.getCustomerId());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }
        
        return mapErrors;
    }
}