package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getCategories();
}
