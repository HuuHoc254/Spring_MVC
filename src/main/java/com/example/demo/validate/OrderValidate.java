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

	public Map<Integer,Map<String,String>> save(List<SaveOrder> saveOrders) {
		Map<Integer,Map<String,String>> mapErrors = new HashMap<Integer, Map<String,String>>();
		boolean check = false;
		for (SaveOrder order: saveOrders) {
			Map<String, String> errors = new HashMap<String, String>(); 
			if (order.getProductCode() == "") {
				errors.put("productCode", "Không được để trống!");
			}else {
				check = productService.getNameByCode(order.getProductCode()) == null;
		        if ( check ) {
		        	errors.put("productCode", "Mã sản phẩm không tồn tại!");
		        }
			}

			check = false;
			if (order.getPhone() == ""){
				errors.put("phone", "Không được để trống!");
			}else {
				check = customerService.getNameByPhone(order.getPhone()) == null;
		        if ( check ) {
		        	errors.put("phone", "Số điện thoại không tồn tại!");
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

	public Map<Integer,Map<String,String>> allocation(List<Allocation> allocaties) {
		Map<Integer,Map<String,String>> mapErrors = new HashMap<Integer, Map<String,String>>();
		boolean check = false;
		for(int i=0;i<allocaties.size()-1;i++) {
			Map<String, String> errors = new HashMap<String, String>(); 
			if (allocaties.get(i).getProductCode() == "") {
				errors.put("productCode", "Không được để trống!");
			}else {
				check = productService.getNameByCode(allocaties.get(i).getProductCode()) == null;
		        if ( check ) {
		        	errors.put("productCode", "Mã sản phẩm không tồn tại!");
		        }
			}
			if (allocaties.get(i).getQuantity() == null) {
				errors.put("quantity", "Không được để trống!");
			}else if (allocaties.get(i).getQuantity() < 1) {
				errors.put("quantity", "Số lượng không hợp lệ!");
			}
			if(!errors.isEmpty()) {
				mapErrors.put(allocaties.get(i).getNumber(), errors);
			}
		}
        return mapErrors;

    }

}