package com.example.java.exercises.task9.dto.order;

import java.time.LocalDateTime;

public record OrderItemDTO(int id, int orderId, int productId, int quantity, double price, LocalDateTime updatedAt, LocalDateTime createdAt) {
}
