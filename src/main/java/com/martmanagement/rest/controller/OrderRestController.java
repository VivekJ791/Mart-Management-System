package com.martmanagement.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.martmanagement.request.OrderDTO;
import com.martmanagement.request.OrderItemDTO;
import com.martmanagement.service.OrderService;

@RestController
@RequestMapping("/api/rest/orders")
public class OrderRestController {

	@Autowired
    private OrderService orderService;
	
	@PostMapping("/place-order")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            for (OrderItemDTO item : orderDTO.getOrderItems()) {
                if (item.getQuantity() < 20) {
                    throw new IllegalArgumentException("Minimum quantity per product should be 20");
                }
            }
            OrderDTO createdOrder = orderService.placeOrder(orderDTO);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@GetMapping("/by-delivery-date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDeliveryDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<OrderDTO> orders = orderService.findOrdersByDeliveryDate(date);
        return ResponseEntity.ok(orders);
    }
	
}
