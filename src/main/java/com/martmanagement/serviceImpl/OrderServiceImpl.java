package com.martmanagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martmanagement.entity.DeliverySlot;
import com.martmanagement.entity.Order;
import com.martmanagement.entity.OrderItem;
import com.martmanagement.entity.Product;
import com.martmanagement.entity.User;
import com.martmanagement.repository.DeliverySlotRepository;
import com.martmanagement.repository.OrderRepository;
import com.martmanagement.repository.ProductRepository;
import com.martmanagement.repository.UserRepository;
import com.martmanagement.request.OrderDTO;
import com.martmanagement.request.OrderItemDTO;
import com.martmanagement.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private ProductRepository productRepository;

	@Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired DeliverySlotRepository deliverySlotRepository;
	
	@Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        LocalDate deliveryDate = orderDTO.getDeliveryDate();
        DeliverySlot deliverySlot = deliverySlotRepository.findByDate(deliveryDate);
                
        if (deliverySlot == null) {
        	throw new RuntimeException("Delivery slot not found for the selected date");
        }

        if (deliverySlot.getCapacity() <= 0) {
            throw new RuntimeException("No available capacity for the selected delivery slot");
        }

        deliverySlot.setCapacity(deliverySlot.getCapacity() - 1);
        deliverySlotRepository.save(deliverySlot);
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product ID " + product.getId());
            }

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product); 

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());

            order.getOrderItems().add(item);
        }

        order = orderRepository.save(order);
        return convertOrderToOrderDTO(order);
    }

	private OrderDTO convertOrderToOrderDTO(Order order) {
		if (order == null) {
			return null;
		}
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setUserId(order.getUser().getId());
		orderDTO.setOrderDate(order.getOrderDate());
	    orderDTO.setDeliveryDate(order.getDeliveryDate());

		List<OrderItemDTO> orderItemDTOList = order.getOrderItems().stream().map(this::convertOrderItemToOrderItemDTO)
				.toList();
		orderDTO.setOrderItems(orderItemDTOList);

		return orderDTO;
	}

	private OrderItemDTO convertOrderItemToOrderItemDTO(OrderItem orderItem) {
		if (orderItem == null) {
			return null;
		}
		OrderItemDTO itemDTO = new OrderItemDTO();
		itemDTO.setId(orderItem.getId());
		itemDTO.setProductId(orderItem.getProduct().getId());
		itemDTO.setQuantity(orderItem.getQuantity());
		return itemDTO;
	}

	@Override
	public List<OrderDTO> findOrdersByDeliveryDate(LocalDate deliveryDate) {
		 List<Order> orders = orderRepository.findByDeliveryDate(deliveryDate);
		    return orders.stream().map(this::convertOrderToOrderDTO).toList();
	}
}
