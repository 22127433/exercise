package com.example.java.exercises.task6.controller;

import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/list")
    public List<CategoryResponseDTO> getCategories(){
        return categoryService.getCategories();
    }
}
