package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.order.OrderDTO;
import com.example.java.exercises.task9.dto.order.OrderItemDTO;
import com.example.java.exercises.task9.dto.order.OrderModifyDTO;
import com.example.java.exercises.task9.entity.Customer;
import com.example.java.exercises.task9.entity.Order;
import com.example.java.exercises.task9.mapper.OrderMapper;
import com.example.java.exercises.task9.repository.CustomerRepository;
import com.example.java.exercises.task9.repository.OrderRepository;
import com.example.java.exercises.task9.service.interfaces.OrderItemService;
import com.example.java.exercises.task9.service.interfaces.OrderService;
import com.example.java.exercises.task9.utils.ReflectionValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemService orderItemService,
            CustomerRepository customerRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(int customerId, OrderModifyDTO orderModifyDTO) {
        try {
            ReflectionValidator.validate(orderModifyDTO);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Order order = Order.builder()
                .customer(customer)
                .status(false)
                .totalPrice(BigDecimal.ZERO)
                .build();

        orderRepository.save(order);

        List<OrderItemDTO> orderItemDTOs = orderItemService.createOrderItem(orderModifyDTO.items(), order);

        double totalPrice = orderItemDTOs
                .stream()
                .mapToDouble(OrderItemDTO::price)
                .sum();

        order.setTotalPrice(BigDecimal.valueOf(totalPrice));
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO getOrder(int orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order"));
        return orderMapper.toDTO(order);
    }

    @Override
    public Page<OrderDTO> getOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        return page.map(orderMapper::toDTO);
    }

    @Override
    @Transactional
    public void markAsPaid(int orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(true);
    }
}
