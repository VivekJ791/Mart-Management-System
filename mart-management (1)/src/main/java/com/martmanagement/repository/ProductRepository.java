package com.martmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
