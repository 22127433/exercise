package com.example.java.exercises.task9.mapper;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Product product, int quantity) {
        return new  ProductDTO(
            product.getId(),
            product.getName(),
            product.getPrice().doubleValue(),
            quantity,
            product.getUpdatedAt(),
            product.getCreatedAt()
        );
    }
}
