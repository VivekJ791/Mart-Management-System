package com.martmanagement.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.martmanagement.entity.Product;
import com.martmanagement.repository.ProductRepository;
import com.martmanagement.request.ProductModel;
import com.martmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	public ProductServiceImpl(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductModel addOrUpdate(final ProductModel productModel) {

		Product product;
		final Long productId = productModel.getId();
		if (productId != null && productId != 0) {
			product = productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException("Product not Found for Id :: " + productId));
		} else {
			product = new Product();
		}
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setStock(productModel.getStock());
		product = productRepository.save(product);
		return this.convert(product);
	}
	
	private ProductModel convert(final Product product) {
		if (product == null) {
			return null;
		}
		final ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setPrice(product.getPrice());
		productModel.setStock(product.getStock());
		productModel.setIsAvailable(product.getStock() != null && product.getStock() > 20);
		return productModel;
	}

	@Override
	public List<ProductModel> getAll() {
		return productRepository.findAll().stream().map(this::convert).toList();
	}

}
