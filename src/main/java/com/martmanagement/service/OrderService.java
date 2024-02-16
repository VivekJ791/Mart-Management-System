package com.martmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.martmanagement.request.OrderDTO;

public interface OrderService {

	OrderDTO placeOrder(OrderDTO orderDTO);
	
	List<OrderDTO> findOrdersByDeliveryDate(LocalDate deliveryDate);

}
