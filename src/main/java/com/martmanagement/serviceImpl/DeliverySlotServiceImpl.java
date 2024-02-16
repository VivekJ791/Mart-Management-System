package com.martmanagement.serviceImpl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.martmanagement.entity.DeliverySlot;
import com.martmanagement.repository.DeliverySlotRepository;
import com.martmanagement.request.DeliverySlotModel;
import com.martmanagement.service.DeliverySlotService;

@Service
public class DeliverySlotServiceImpl implements DeliverySlotService {

	private final DeliverySlotRepository deliverySlotRepository;

	public DeliverySlotServiceImpl(final DeliverySlotRepository deliverySlotRepository) {
		this.deliverySlotRepository = deliverySlotRepository;
	}

	@Override
	public DeliverySlotModel addDeliverySlot(final DeliverySlotModel deliverySlotModel) {
		DeliverySlot deliverySlot;
		final Long deliverySlotId = deliverySlotModel.getId();
		if (deliverySlotId != null && deliverySlotId != 0) {
			deliverySlot = deliverySlotRepository.findById(deliverySlotId)
					.orElseThrow(() -> new RuntimeException("DeliverySlot not Found for Id :: " + deliverySlotId));
		} else {
			deliverySlot = new DeliverySlot();
		}
		deliverySlot.setCapacity(deliverySlotModel.getCapacity());
		deliverySlot.setDate(deliverySlotModel.getDate());
		deliverySlot = deliverySlotRepository.save(deliverySlot);
		return this.convert(deliverySlot);
	}

	@Override
	public DeliverySlotModel getDeliverySlotsByDate(final LocalDate localDate) {
		return convert(deliverySlotRepository.findByDate(localDate));
	}

	private DeliverySlotModel convert(final DeliverySlot deliverySlot) {
		if (deliverySlot == null) {
			return null;
		}
		final DeliverySlotModel deliverySlotModel = new DeliverySlotModel();
		deliverySlotModel.setId(deliverySlot.getId());
		deliverySlotModel.setDate(deliverySlot.getDate());
		deliverySlotModel.setCapacity(deliverySlot.getCapacity());
		return deliverySlotModel;
	}
}
