package com.example.java.exercises.task9.dto.order;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(int id, int customerId, boolean status, double totalPrice, LocalDateTime updatedAt, LocalDateTime createdAt, List<OrderItemDTO> orderItemDTOs) {
}
