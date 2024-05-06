package com.example.demo.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Allocation;
import com.example.demo.dto.SaveOrder;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IProductService;

@Component
public class OrderValidate {

	@Autowired
    private IProductService productService;
	@Autowired
    private ICustomerService customerService;

	public Map<Integer,Map<String,String>> validateSaveOrder(List<SaveOrder> saveOrders) {
		Map<Integer,Map<String,String>> mapErrors = new HashMap<Integer, Map<String,String>>();
		boolean check = false;
		for (SaveOrder order: saveOrders) {
			Map<String, String> errors = new HashMap<String, String>(); 
			if (order.getProductCode().trim() == "") {
				errors.put("productCode", "Không được để trống!");
			}else {
				check = productService.getProductNameByCode(order.getProductCode()) == null;
		        if ( check ) {
		        	errors.put("productCode", "Mã sản phẩm không tồn tại!");
		        }
			}

			check = false;
			if (order.getPhoneNumber().trim() == ""){
				errors.put("phoneNumber", "Không được để trống!");
			}else {
				check = customerService.getCustomerNameByPhoneNumber(order.getPhoneNumber()) == null;
		        if ( check ) {
		        	errors.put("phoneNumber", "Số điện thoại không tồn tại!");
		        }
			}
			if (order.getQuantity() < 1) {
				errors.put("quantity", "Số lượng không hợp lệ!");
			}
			if(!errors.isEmpty()) {
				mapErrors.put(order.getIndex(), errors);
			}
		}
        return mapErrors;
 
    }

	public Map<Integer,Map<String,String>> validateAllocation(List<Allocation> allocaties) {
		Map<Integer,Map<String,String>> mapErrors = new HashMap<Integer, Map<String,String>>();
		boolean check = false;
		for (Allocation allocate: allocaties) {
			Map<String, String> errors = new HashMap<String, String>(); 
			if (allocate.getProductCode().trim() == "") {
				errors.put("productCode", "Không được để trống!");
			}else {
				check = productService.getProductNameByCode(allocate.getProductCode()) == null;
		        if ( check ) {
		        	errors.put("productCode", "Mã sản phẩm không tồn tại!");
		        }
			}
			if (allocate.getQuantity() < 1) {
				errors.put("quantity", "Số lượng không hợp lệ!");
			}
			if(!errors.isEmpty()) {
				mapErrors.put(allocate.getIndex(), errors);
			}
		}
        return mapErrors;

    }

}