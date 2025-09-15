package com.example.java.exercises.task6.controller;

import com.example.java.exercises.task6.dto.ProductCreateDTO;
import com.example.java.exercises.task6.dto.ProductDTO;
import com.example.java.exercises.task6.entity.Product;
import com.example.java.exercises.task6.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/get/{id}")
    public ProductDTO findByProductId(@PathVariable int id){
        return productService.findByProductId(id);
    }

    @PostMapping(value = "/create")
    public ProductDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        return productService.createProduct(productCreateDTO);
    }

    @PutMapping(value = "/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
}
