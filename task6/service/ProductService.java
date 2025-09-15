package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.ProductCreateDTO;
import com.example.java.exercises.task6.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO findByProductId(int productId);
    ProductDTO createProduct(ProductCreateDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    void deleteProduct(int productId);
}
