package com.example.java.exercises.task9.dto.order;

public record OrderItemDTO(int id, int orderId, int productId, int quantity, double price, String updatedAt, String createdAt) {
}
