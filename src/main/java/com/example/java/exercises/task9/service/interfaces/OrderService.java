package com.example.java.exercises.task9.service.interfaces;

import com.example.java.exercises.task9.dto.order.OrderDTO;
import com.example.java.exercises.task9.dto.order.OrderModifyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDTO createOrder(int customerId, OrderModifyDTO orderModifyDTO);
    OrderDTO getOrder(int orderId);
    Page<OrderDTO> getOrders(Pageable pageable);
    void markAsPaid(int orderId);
}
