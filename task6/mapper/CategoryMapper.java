package com.example.java.exercises.task6.mapper;

import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.dto.ProductDTO;
import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category category, List<Product> productList){
        List<ProductDTO> productDTOList = productList
                .stream()
                .map(product -> new ProductDTO(
                        product.getProduct_id(),
                        product.getProduct_name(),
                        product.getProduct_description(),
                        product.getProduct_price(),
                        product.getCategory_id()))
                .toList();

        return  new CategoryResponseDTO(
                category.getCategory_id(),
                category.getCategory_name(),
                category.getCategory_description(),
                productDTOList
        );
    }
}
