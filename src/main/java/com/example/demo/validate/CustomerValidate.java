package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CreateCustomer;
import com.example.demo.dto.UpdateCustomer;
import com.example.demo.service.ICustomerService;

@Component
public class CustomerValidate {

	@Autowired
    private ICustomerService customerService;

	public Map<String,String> create(CreateCustomer customer) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(customer.getName() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(customer.getAddress() == "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(customer.getPhone() == "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else if(!customer.getPhone().matches( "^0\\d{9}$" )){
        	mapErrors.put("phone", "Sai định dạng số điện thoại!");
        } else {
	        check = customerService.existsByPhone(customer.getPhone());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }

	public Map<String,String> update(UpdateCustomer customer) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(customer.getName() == "") {
        	mapErrors.put("customerName", "Không được để trống tên khách hàng!");
        }

        if(customer.getAddress() == "") {
        	mapErrors.put("address", "Không được để địa chỉ khách hàng!");
        }

        if(customer.getPhone() == "") {
        	mapErrors.put("phoneNumber", "Không được để trống số điện thoại của khách hàng!");
        } else if(!customer.getPhone().matches( "^0\\d{9}$" )){
        	mapErrors.put("phone", "Sai định dạng số điện thoại!");
        } else {
	        check = customerService.existsByPhoneNotId(customer.getPhone(), customer.getId());
	        if ( check ) {
	            mapErrors.put("phoneNumber", "Số điện thoại đã tồn tại!");
	        }
        }

        return mapErrors;
    }
}