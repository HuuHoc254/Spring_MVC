package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CreateProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.service.IProductService;

@Component
public class ProductValidate {

	@Autowired
    private IProductService productService;

	public Map<String,String> create(CreateProduct create) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(create.getCode().trim() == "") {
        	mapErrors.put("productCode", "Không được để trống mã sản phẩm!.");
        }else {
	        check = productService.existsByCode(create.getCode());
	        if ( check ) {
	            mapErrors.put("productCode", "Mã sản phẩm đã tồn tại!.");
	        }
        }

        if(create.getName().trim()== "") {
        	mapErrors.put("productName", "Không được để trống tên sản phẩm!.");
        }else {
	        check = productService.existsByName(create.getName());
	        if ( check ) {
	        	mapErrors.put("productName", "Tên sản phẩm đã tồn tại!.");
	        }
        }

        if(create.getPurchasePrice() == null) {
        	mapErrors.put("purchasePrice", "Không được để trống giá mua vào!.");
        }

        if(create.getSalePrice() == null) {
        	mapErrors.put("salePrice", "Không được để trống giá bán ra!.");
        } else if (create.getSalePrice() != null && create.getSalePrice()<= create.getPurchasePrice()) {
        	mapErrors.put("salePrice", "Giá bán phải lớn hơn giá mua!.");
        }
        if(create.getInventoryQuantity()==null) {
        	create.setInventoryQuantity(0);
        }else if(create.getInventoryQuantity()<0){
        	mapErrors.put("inventoryQuantity", "Số lượng tồn kho không hợp lệ.");
        }
        return mapErrors;
    }
	
	public Map<String,String> update(UpdateProduct update) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(update.getCode().trim() == "") {
        	mapErrors.put("productCode", "Không được để trống mã sản phẩm!.");
        }else {
	        check = productService.existsByCodeNotId(update.getCode(),update.getId());
	        if ( check ) {
	            mapErrors.put("productCode", "Mã sản phẩm đã tồn tại!.");
	        }
        }

        if(update.getName().trim() == "") {
        	mapErrors.put("productName", "Không được để trống tên sản phẩm!.");
        }else {
	        check = productService.existsByNameNotId(update.getName(),update.getId());
	        if ( check ) {
	        	mapErrors.put("productName", "Tên sản phẩm đã tồn tại!.");
	        }
        }

        if(update.getPurchasePrice() == null) {
        	mapErrors.put("purchasePrice", "Không được để trống giá mua vào!.");
        }else if(update.getPurchasePrice() <= 0){
        	mapErrors.put("purchasePrice", "Giá mua vào phải lớn hơn 0!.");
        }

        if(update.getSalePrice() == null) {
        	mapErrors.put("salePrice", "Không được để trống giá bán ra!.");
        }
        if(update.getPurchasePrice() != null && update.getSalePrice() != null) {
	        if ( update.getSalePrice()<= update.getPurchasePrice()) {
	        	mapErrors.put("salePrice", "Giá bán phải lớn hơn giá mua!.");
	        }
        }
        return mapErrors;
    }

}