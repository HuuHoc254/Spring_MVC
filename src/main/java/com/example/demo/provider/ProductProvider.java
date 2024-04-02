	package com.example.demo.provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ProductProvider implements ProviderMethodResolver{
	    public String countSearch(String productCode, String productName) {
	        return new SQL() {{
	            SELECT("COUNT(*)");
	            FROM("products");
	            if (productCode != null) {
	                WHERE("product_code LIKE CONCAT(#{productCode}, '%')");
	            }
	            if (productName != null) {
	                WHERE("product_name LIKE CONCAT(#{productName}, '%')");
	            }
	        }}.toString();
	    }

	    public String search(String productCode, String productName, int pageNumber) {
	        return new SQL() {{
	            SELECT("product_id, product_code, product_name, purchase_price, sale_price, inventory_quantity");
	            FROM("products");
	            if (productCode != null) {
	                WHERE("product_code LIKE CONCAT(#{productCode}, '%')");
	            }
	            if (productName != null) {
	                WHERE("product_name LIKE CONCAT(#{productName}, '%')");
	            }
	            LIMIT(3);
	            OFFSET((pageNumber-1)*3);
	        }}.toString();
	    }
	}