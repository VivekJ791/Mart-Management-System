package com.martmanagement.rest.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.martmanagement.request.DeliverySlotModel;
import com.martmanagement.service.DeliverySlotService;

@RestController
@RequestMapping("api/rest/delivery-slot")
public class DeliverySlotRestController {

	private final DeliverySlotService deliverySlotService;

	public DeliverySlotRestController(final DeliverySlotService deliverySlotService) {
		this.deliverySlotService = deliverySlotService;
	}
	
	@PostMapping("/addOrUpdate")
    public ResponseEntity<Object> addDeliverySlot(@RequestBody final DeliverySlotModel deliverySlotModel) {
        return ResponseEntity.ok(deliverySlotService.addDeliverySlot(deliverySlotModel));
    }
	
	@GetMapping("/delivery-slots")
    public ResponseEntity<Object> getDeliverySlotsByDate(@RequestParam("date") final LocalDate date) {
        return ResponseEntity.ok(deliverySlotService.getDeliverySlotsByDate(date));
    }
	
}
