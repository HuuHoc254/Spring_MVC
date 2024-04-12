package com.example.demo.validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.InsertProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.service.IProductService;

@Component
public class ProductValidate {

	@Autowired
    private IProductService productService;

	public Map<String,String> validateInsertProduct(InsertProduct insertProductRequest) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(insertProductRequest.getProductCode() == "") {
        	mapErrors.put("productCode", "Không được để trống mã sản phẩm!.");
        }else {
	        check = productService.existsByProductCode(insertProductRequest.getProductCode());
	        if ( check ) {
	            mapErrors.put("productCode", "Mã sản phẩm đã tồn tại!.");
	        }
        }

        if(insertProductRequest.getProductName()== "") {
        	mapErrors.put("productName", "Không được để trống tên sản phẩm!.");
        }else {
	        check = productService.existsByProductName(insertProductRequest.getProductName());
	        if ( check ) {
	        	mapErrors.put("productName", "Tên sản phẩm đã tồn tại!.");
	        }
        }

        if(insertProductRequest.getPurchasePrice()== null) {
        	mapErrors.put("purchasePrice", "Không được để trống giá mua vào!.");
        }

        if(insertProductRequest.getSalePrice()== null) {
        	mapErrors.put("salePrice", "Không được để trống giá bán ra!.");
        } else if (insertProductRequest.getSalePrice()<= insertProductRequest.getPurchasePrice()) {
        	mapErrors.put("salePrice", "Giá bán phải lớn hơn giá mua!.");
        }
        if(insertProductRequest.getInventoryQuantity()==null) {
        	insertProductRequest.setInventoryQuantity(0);
        }
        return mapErrors;
    }
	
	public Map<String,String> validateUpdateProduct(UpdateProduct updateProductRequest) {
        Map<String, String> mapErrors = new HashMap<String, String>();
        boolean check = false;
        if(updateProductRequest.getProductCode() == "") {
        	mapErrors.put("productCode", "Không được để trống mã sản phẩm!.");
        }else {
	        check = productService.existsByProductCodeNotId(updateProductRequest.getProductCode(),updateProductRequest.getProductId());
	        if ( check ) {
	            mapErrors.put("productCode", "Mã sản phẩm đã tồn tại!.");
	        }
        }

        if(updateProductRequest.getProductName()== "") {
        	mapErrors.put("productName", "Không được để trống tên sản phẩm!.");
        }else {
	        check = productService.existsByProductNameNotId(updateProductRequest.getProductName(),updateProductRequest.getProductId());
	        if ( check ) {
	        	mapErrors.put("productName", "Tên sản phẩm đã tồn tại!.");
	        }
        }

        if(updateProductRequest.getPurchasePrice()== null) {
        	mapErrors.put("purchasePrice", "Không được để trống giá mua vào!.");
        }

        if(updateProductRequest.getSalePrice()== null) {
        	mapErrors.put("salePrice", "Không được để trống giá bán ra!.");
        } else if (updateProductRequest.getSalePrice()<= updateProductRequest.getPurchasePrice()) {
        	mapErrors.put("salePrice", "Giá bán phải lớn hơn giá mua!.");
        }
        return mapErrors;
    }

}