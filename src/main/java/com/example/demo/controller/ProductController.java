package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CreateProduct;
import com.example.demo.dto.UpdateProduct;
import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import com.example.demo.service.impl.AuthService;
import com.example.demo.validate.ProductValidate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ProductController {
	final int LIMIT = 3;
	@Autowired
	private AuthService authService;
	
    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidate validate;

    @GetMapping("/product")
    private String search(HttpServletRequest request
    							, Model model
    							, @RequestParam(defaultValue = "") String code
    							, @RequestParam(defaultValue = "") String name
    							, @RequestParam(defaultValue = "1") int page
    							){
    	try {
    		code = code.trim();
    		name = name.trim();
    		HttpSession session = request.getSession();
        	if(page < 1) {
        		page = 1;
        	}
        	session.setAttribute("productCode", code);
        	session.setAttribute("productName", name);
        	session.setAttribute("currentPage", page);

            int totalRecord = productService.countSearch( code, name );
            int totalPage = totalRecord % LIMIT == 0 ? totalRecord / LIMIT : totalRecord / LIMIT + 1;
            List<Product> products = productService.search(code, name, page);
            model.addAttribute("isAdmin", authService.isAdmin());
            model.addAttribute("code", code);
            model.addAttribute("name", name);
            model.addAttribute("currentPage", page);
            model.addAttribute("products", products);
            model.addAttribute("totalPage",totalPage);
            model.addAttribute("successMessage", session.getAttribute("successMessage"));
            model.addAttribute("url","product");
            session.removeAttribute("successMessage");
            return "product/list";
    	}catch (Exception e) {
    		model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    }

    @GetMapping("/product/cancel")
    private String cancel( HttpServletRequest request, Model model){
    	HttpSession session = request.getSession();
    	String code = (String) session.getAttribute("productCode");
    	String name = (String) session.getAttribute("productName");
    	int page = 1;
    	if(session.getAttribute("currentPage") !=null ) {
    		page = (int) session.getAttribute("currentPage");
    	}
    	String url = "redirect:/product?page="+page;
    	if( !code.trim().equals("") ) {
    		url += "&code="+code;
    	}
    	if( !name.trim().equals("") ) {
    		url += "&name="+name;
    	}
		return url;
    }

    @GetMapping("/admin/product/create")
    private String showFormCreate(Model model){
    	model.addAttribute("product", new CreateProduct());
    	model.addAttribute("mapErrors",new HashMap<String, String>());
    	model.addAttribute("isAdmin", authService.isAdmin());
    	model.addAttribute("url","product");
	    return "product/create";
    }

    @PostMapping("/admin/product/store")
    private String store(Model model, @ModelAttribute CreateProduct product){
    	try {
    		Map<String, String> mapErrors = validate.create(product);
        	if(mapErrors.size() == 0) {
        		if(productService.create(product)) {
        			model.addAttribute("product",new CreateProduct());
        			model.addAttribute("successMessage", "Thêm mới sản phẩm thành công!");
        		};
        	} else {
        		model.addAttribute("product",product);
        	}
        	model.addAttribute("isAdmin", authService.isAdmin());
        	model.addAttribute("url", "product");
        	model.addAttribute("mapErrors",mapErrors);
        	return "product/create";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}

    }

    @GetMapping("/admin/product/edit/{id}")
    private String showFormEdit(Model model, @PathVariable("id") int id){
    	try {
    		Product product = productService.getById(id);
        	model.addAttribute("mapErrors",new HashMap<String, String>());
        	model.addAttribute("product", product);
        	model.addAttribute("isAdmin", authService.isAdmin());
        	model.addAttribute("url","product");
    	    return "product/edit";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    	
    }
    
    @PostMapping("admin/product/update/{id}")
    private String update( 	HttpServletRequest request
						, 	Model model
						, 	@ModelAttribute UpdateProduct product
						, 	@PathVariable("id") int id
							){
    	try {
    		HttpSession session = request.getSession();
        	String code = (String) session.getAttribute("productCode");
        	String name = (String) session.getAttribute("productName");
        	int page = 1;
        	if(session.getAttribute("currentPage") !=null ) {
        		page = (int) session.getAttribute("currentPage");
        	}
        	String url = "redirect:/product?page="+page;
        	if( !code.equals("") ) {
        		url += "&code="+code;
        	}
        	if( !name.equals("") ) {
        		url += "&name="+name;
        	}

        	Map<String, String> mapErrors = validate.update(product);
    		if( mapErrors.size() == 0) {
    			if (productService.update(product)) {
	    			session.setAttribute("successMessage", "Cập nhật sản phẩm thành công!");
	    			return url;
	    		} else {
	    			throw new Exception("Đã có sự thay đổi dữ liệu, xin vui vòng thử lại!");
	    		}
    		}
        	model.addAttribute("product",product); 
        	model.addAttribute("mapErrors",mapErrors);
        	model.addAttribute("isAdmin", authService.isAdmin());
        	model.addAttribute("url","product");
        	return "product/edit";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    }
    
    @GetMapping("/admin/product/destroy/{id}")
    private String delete( 	HttpServletRequest request
						, 	Model model
						, 	@PathVariable("id") int id
							){
    	try {
    		HttpSession session = request.getSession();
        	boolean check = productService.delete(id);
        	if (check) {
        		session.setAttribute("successMessage", "Đã xóa sản phẩm thành công!");
        	}else {
        		throw new RuntimeException("Sản phẩm không tồn tại!");
        	}
        	String code = (String) session.getAttribute("productCode");
        	String name = (String) session.getAttribute("productName");
        	int page = (int) session.getAttribute("currentPage");
        	String url = "redirect:/product?page="+page;
        	if( !code.trim().equals("") ) {
        		url += "&code="+code;
        	}
        	if( !name.trim().equals("") ) {
        		url += "&name="+name;
        	}
    	    return url;
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
    		return "auth/errors";
		}
    	
    }

}
