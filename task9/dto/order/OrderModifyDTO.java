package com.example.java.exercises.task9.dto.order;
import java.util.List;

public record OrderModifyDTO(List<OrderItemModifyDTO> items) {
}
