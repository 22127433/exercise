package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.CategoryCreateDTO;
import com.example.java.exercises.task6.dto.CategoryResponseDTO;
import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.entity.Product;
import com.example.java.exercises.task6.mapper.CategoryMapper;
import com.example.java.exercises.task6.repository.CategoryRepository;
import com.example.java.exercises.task6.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .findAll()
                .stream()
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.findProductsByCategory(category)))
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesEven(){
        return categoryRepository
                .findAll()
                .stream()
                .filter(category -> category.getCategory_id() % 2 == 0)
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.findProductsByCategory(category)))
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesProductsEven(){
        return categoryRepository
                .findAll()
                .stream()
                .filter(category -> category.getCategory_id() % 2 == 0)
                .map(
                category -> categoryMapper.categoryToCategoryResponseDTO(
                        category,
                        productRepository.findProductsByCategory(category)
                                .stream()
                                .filter(product -> product.getProductId() % 2 == 0)
                                .toList()))
                .toList();
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO){
        Category category = categoryRepository.save(categoryMapper.categoryCreateDTOToCategory(categoryCreateDTO));
        return categoryMapper.categoryToCategoryResponseDTO(category, category.getProducts());
    }

    @Override
    @Transactional
    public CategoryResponseDTO addProductsToCategory(int id,  List<Integer> productIds){
        Optional <Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            List<Product> products = productRepository.findAllById(productIds);
            products.forEach(product -> product.setCategory(category.get()));
            productRepository.saveAll(products);
            return categoryMapper.categoryToCategoryResponseDTO(category.get(), products);
        }
        return null;
    }

    @Override
    @Transactional
    public CategoryResponseDTO deleteProductsFromCategory(int id, List<Integer> productIds){
        Optional <Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            List<Product> products = productRepository.findAllById(productIds);
            products.forEach(product -> product.setCategory(null));
            productRepository.saveAll(products);
            return categoryMapper.categoryToCategoryResponseDTO(category.get(), products);
        }
        return null;
    }

    @Override
    @Transactional
    public CategoryResponseDTO replaceProducts(int id, List<Integer> productIds){
        Optional <Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            List<Product> newProducts = productRepository.findAllById(productIds);
            newProducts.forEach(newProduct -> newProduct.setCategory(category.get()));
            productRepository.saveAll(newProducts);
            List<Product> oldProducts = productRepository.findProductsByCategory(category.get());
            oldProducts.forEach(oldProduct -> oldProduct.setCategory(null));
            productRepository.saveAll(oldProducts);
            return categoryMapper.categoryToCategoryResponseDTO(category.get(), newProducts);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteCategory(int id){
        replaceProducts(id, List.of());
        categoryRepository.deleteById(id);
    }
}
