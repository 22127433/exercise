package com.example.java.exercises.task9.dto.product;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
