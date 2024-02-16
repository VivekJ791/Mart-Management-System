package com.martmanagement.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverySlotModel {

	private Long id;
    private LocalDate date;
    private Integer capacity; 
}
