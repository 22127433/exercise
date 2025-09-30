package com.example.java.exercises.task9.service.interfaces;

import com.example.java.exercises.task9.dto.order.OrderItemDTO;
import com.example.java.exercises.task9.dto.order.OrderItemModifyDTO;
import com.example.java.exercises.task9.entity.Order;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> createOrderItem(List<OrderItemModifyDTO> orderItemDTO, Order order);
    void checkoutOrderItems(List<OrderItemDTO> orderItemDTOs);
}
