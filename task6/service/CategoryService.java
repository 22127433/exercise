package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.CategoryCreateDTO;
import com.example.java.exercises.task6.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getCategories();
    List<CategoryResponseDTO> getCategoriesEven();
    List<CategoryResponseDTO> getCategoriesProductsEven();
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);
    CategoryResponseDTO addProductsToCategory(int id, List<Integer> productIds);
    CategoryResponseDTO deleteProductsFromCategory(int id, List<Integer> productIds);
    CategoryResponseDTO replaceProducts(int id, List<Integer> productIds);
    void deleteCategory(int id);
}
