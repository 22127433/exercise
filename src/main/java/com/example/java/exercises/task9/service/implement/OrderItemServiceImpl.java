package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.order.OrderItemDTO;
import com.example.java.exercises.task9.dto.order.OrderItemModifyDTO;
import com.example.java.exercises.task9.entity.Order;
import com.example.java.exercises.task9.entity.OrderItem;
import com.example.java.exercises.task9.entity.Product;
import com.example.java.exercises.task9.mapper.OrderMapper;
import com.example.java.exercises.task9.repository.OrderItemRepository;
import com.example.java.exercises.task9.repository.ProductRepository;
import com.example.java.exercises.task9.service.interfaces.OrderItemService;
import com.example.java.exercises.task9.service.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    public OrderItemServiceImpl(
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            OrderMapper orderMapper,
            ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.productService = productService;
    }

    @Override
    @Transactional
    public List<OrderItemDTO> createOrderItem(List<OrderItemModifyDTO> orderItemModifyDTOs, Order order) {
        List<OrderItem> orderItems = orderItemModifyDTOs
                .stream()
                .map(dto -> {
                    Product product = productRepository.findById(dto.productId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    if (product.getInventory().getQuantity() < dto.quantity()) {
                        throw new RuntimeException("Product quantity less than or equal to inventory quantity");
                    }

                    return OrderItem.builder()
                            .order(order)
                            .product(product)
                            .price(product.getPrice().multiply(BigDecimal.valueOf(dto.quantity())))
                            .quantity(dto.quantity())
                            .build();
                })
                .toList();

        order.setOrderItems(new ArrayList<>(orderItems));

        orderItemRepository.saveAll(orderItems);
        return orderItems
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void checkoutOrderItems(List<OrderItemDTO> orderItemDTOs) {
        orderItemDTOs.forEach(
                itemDTO -> productService.adjustInventoryQuantity(
                        itemDTO.productId(),
                        itemDTO.quantity(),
                        false
                )
        );
    }
}
