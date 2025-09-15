package com.example.java.exercises.task6.controller;

import com.example.java.exercises.task6.dto.CategoryCreateDTO;
import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.service.CategoryService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/list/even_category_id")
    public List<CategoryResponseDTO> getCategoriesEven(){
        return categoryService.getCategoriesEven();
    }

    @GetMapping(value = "/list/even_product_category_id")
    public List<CategoryResponseDTO> getCategoriesProductsEven(){
        return categoryService.getCategoriesProductsEven();
    }

    @PostMapping(value = "/create")
    public CategoryResponseDTO createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        return categoryService.createCategory(categoryCreateDTO);
    }

    @PutMapping(value = "/add_products/{id}")
    public CategoryResponseDTO addProducts(@PathVariable("id") int id, @RequestBody List<Integer> productIds){
        return categoryService.addProductsToCategory(id, productIds);
    }

    @PutMapping(value = "/delete_products/{id}")
    public CategoryResponseDTO deleteProducts(@PathVariable("id") int id, @RequestBody List<Integer> productIds){
        return categoryService.deleteProductsFromCategory(id, productIds);
    }

    @PutMapping(value = "/replace_products/{id}")
    public CategoryResponseDTO replaceProductsInCategory(@PathVariable("id") int id, @RequestBody List<Integer> productIds){
        return categoryService.replaceProducts(id, productIds);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
    }
}
