package com.martmanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "delivery_slot")
public class DeliverySlot extends BaseEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date;
    private Integer capacity; 

}
