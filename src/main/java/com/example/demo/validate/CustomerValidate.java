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

	public Map<String,String> create(InsertCustomer create) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(create.getCustomerName().trim() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(create.getAddress().trim() == "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(create.getPhoneNumber().trim() == "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else {
	        check = customerService.existsByPhone(create.getPhoneNumber());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

	public Map<String,String> update(UpdateCustomer update) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(update.getCustomerName().trim() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(update.getAddress().trim() == "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(update.getPhoneNumber().trim() == "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else {
	        check = customerService.existsByPhoneNotId(update.getPhoneNumber(), update.getCustomerId());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }
}