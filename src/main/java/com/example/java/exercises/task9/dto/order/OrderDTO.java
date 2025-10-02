package com.example.java.exercises.task9.dto.order;

import java.util.List;

public record OrderDTO(int id, int customerId, boolean status, double totalPrice, String updatedAt, String createdAt, List<OrderItemDTO> orderItemDTOs) {
}
