package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.order.OrderDTO;
import com.example.java.exercises.task9.service.interfaces.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public CheckoutServiceImpl(PaymentService paymentService, OrderService orderService, OrderItemService orderItemService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkout(int orderId, int paymentAccountId) {
        OrderDTO orderDTO = orderService.getOrder(orderId);
        orderItemService.checkoutOrderItems(orderDTO.orderItemDTOs());
        paymentService.charge(paymentAccountId, BigDecimal.valueOf(orderDTO.totalPrice()), false);
        orderService.markAsPaid(orderId);
    }
}
