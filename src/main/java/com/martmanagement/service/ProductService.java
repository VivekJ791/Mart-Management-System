package com.martmanagement.service;

import java.util.List;

import com.martmanagement.request.ProductModel;

public interface ProductService {
	
	ProductModel addOrUpdate(ProductModel productRequest);

	List<ProductModel> getAll();
	
}
