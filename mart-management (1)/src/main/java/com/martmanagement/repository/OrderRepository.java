package com.martmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martmanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByDeliveryDate(LocalDate deliveryDate);

}
