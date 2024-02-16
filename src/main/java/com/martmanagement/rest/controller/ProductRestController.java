package com.martmanagement.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martmanagement.request.ProductModel;
import com.martmanagement.service.ProductService;

@RestController
@RequestMapping("api/rest/product")
public class ProductRestController {

	private final ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/addOrUpdate")
    public ResponseEntity<Object> addProduct(@RequestBody ProductModel productModel) {
        return ResponseEntity.ok(productService.addOrUpdate(productModel));
    }

	@GetMapping("/list")
	public ResponseEntity<Object> addProduct() {
        return ResponseEntity.ok(productService.getAll());
    }
}
