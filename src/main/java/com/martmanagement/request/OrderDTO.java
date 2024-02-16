package com.martmanagement.request;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Long id;
	private Long userId;
	private List<OrderItemDTO> orderItems;
	private LocalDate orderDate;
    private LocalDate deliveryDate;
}
