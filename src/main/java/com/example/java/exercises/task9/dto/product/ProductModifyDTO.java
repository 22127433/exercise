package com.example.java.exercises.task9.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModifyDTO {
    private String name;
    private double price;
    private int quantity;
}