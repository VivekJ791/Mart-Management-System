package com.martmanagement.service;

import java.time.LocalDate;

import com.martmanagement.request.DeliverySlotModel;

public interface DeliverySlotService {

	DeliverySlotModel addDeliverySlot(DeliverySlotModel deliverySlotRequest);
    DeliverySlotModel getDeliverySlotsByDate(LocalDate localDate);
    
}
