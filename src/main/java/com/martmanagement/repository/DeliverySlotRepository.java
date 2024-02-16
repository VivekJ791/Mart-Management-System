package com.martmanagement.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martmanagement.entity.DeliverySlot;

public interface DeliverySlotRepository extends JpaRepository<DeliverySlot, Long> {

	DeliverySlot findByDate(LocalDate date);
}
