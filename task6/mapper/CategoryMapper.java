package com.example.java.exercises.task6.mapper;

import com.example.java.exercises.task6.dto.CategoryCreateDTO;
import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.dto.ProductDTO;
import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category category, List<Product> productList){
        List<ProductDTO> productDTOList = productList
                .stream()
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProduct_name(),
                        product.getProduct_description(),
                        product.getProduct_price(),
                        product.getCategory() != null ? product.getCategory().getCategory_id() : -1))
                .toList();

        return  new CategoryResponseDTO(
                category.getCategory_id(),
                category.getCategory_name(),
                category.getCategory_description(),
                productDTOList
        );
    }

    public Category categoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO){
        Category category = new Category();
        category.setCategory_name(categoryCreateDTO.category_name());
        category.setCategory_description(categoryCreateDTO.category_description());
        category.setProducts(new ArrayList<>());
        return category;
    }
}
