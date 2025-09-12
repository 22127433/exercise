package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.mapper.CategoryMapper;
import com.example.java.exercises.task6.repository.CategoryRepository;
import com.example.java.exercises.task6.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,  ProductRepository productRepository,  CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponseDTO> getCategories(){
        return categoryRepository
                .getAllCategories()
                .stream()
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.getProductsByCategoryId(category.getCategory_id())))
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesEven(){
        return categoryRepository
                .getAllCategories()
                .stream()
                .filter(category -> category.getCategory_id() % 2 == 0)
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.getProductsByCategoryId(category.getCategory_id())))
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesProductsEven(){
        return categoryRepository
                .getAllCategories()
                .stream()
                .filter(category -> category.getCategory_id() % 2 == 0)
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.getProductsByCategoryId(category.getCategory_id())
                                .stream()
                                .filter(product -> product.getProduct_id() % 2 == 0)
                                .toList()))
                .toList();
    }
}
