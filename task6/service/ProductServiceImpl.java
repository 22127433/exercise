package com.example.java.exercises.task6.service;

import com.example.java.exercises.task6.dto.ProductCreateDTO;
import com.example.java.exercises.task6.dto.ProductDTO;
import com.example.java.exercises.task6.entity.Category;
import com.example.java.exercises.task6.entity.Product;
import com.example.java.exercises.task6.mapper.ProductMapper;
import com.example.java.exercises.task6.repository.CategoryRepository;
import com.example.java.exercises.task6.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO findByProductId (int productId){
        Optional<Product> product = productRepository.findById(productId);
        ProductDTO productDTO = null;
        if (product.isPresent()){
            productDTO = productMapper.productToProductDTO(product.get());
        }
        return productDTO;
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productDTO){
        Product product = productMapper.productCreateDTOToProduct(productDTO);
        Optional <Category> category = categoryRepository.findById(productDTO.category_id());

        if (category.isPresent()){
            product.setCategory(category.get());
            System.out.println(product);
            productRepository.save(product);
        }
        return  productMapper.productToProductDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = productMapper.productDTOToProduct(productDTO);

        Optional <Category> category = categoryRepository.findById(productDTO.category_id());
        if (category.isPresent()){
            product.setCategory(category.get());
            productRepository.save(product);
        }
        return  productMapper.productToProductDTO(product);
    }

    @Override
    @Transactional
    public void deleteProduct (int productId){
        productRepository.deleteById(productId);
    }
}
