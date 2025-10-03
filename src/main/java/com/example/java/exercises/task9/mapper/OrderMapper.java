package com.example.java.exercises.task9.mapper;

import com.example.java.exercises.task9.dto.order.OrderDTO;
import com.example.java.exercises.task9.dto.order.OrderItemDTO;
import com.example.java.exercises.task9.entity.Order;
import com.example.java.exercises.task9.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.isStatus(),
                order.getTotalPrice().doubleValue(),
                order.getUpdatedAt(),
                order.getCreatedAt(),
                order.getOrderItems().stream().map(this::toDTO).toList()
        );
    }

    public OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
            orderItem.getId(),
            orderItem.getOrder().getId(),
            orderItem.getProduct().getId(),
            orderItem.getQuantity(),
            orderItem.getPrice().doubleValue(),
            orderItem.getUpdatedAt(),
            orderItem.getCreatedAt()
        );
    }
}
