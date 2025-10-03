package com.example.java.exercises.task9.controller;

import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.dto.product.ProductParamRequest;
import com.example.java.exercises.task9.service.interfaces.ProductService;
import com.example.java.exercises.task9.utils.ReflectionValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product-task-9")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id){
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO == null){
            return ResponseEntity.notFound().build();
        }
        // message, code, data tạo 1 object để mọi return đều chung 1 format object
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductModifyDTO productModifyDTO){
        try {
            ReflectionValidator.validate(productModifyDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ProductDTO productDTO = productService.createProduct(productModifyDTO);
        if (productDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductModifyDTO productModifyDTO){
        try {
            ReflectionValidator.validate(productModifyDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            productService.updateProduct(id, productModifyDTO);
            return ResponseEntity.ok("Product updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted");
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDTO>>  getProductsLikeName(@RequestParam String name){
        List<ProductDTO> productDTOS = productService.getProductsLikeName(name);
        if (productDTOS == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<ProductDTO>> filterProducts(@ModelAttribute ProductParamRequest params){
        List<ProductDTO> productDTOS = productService.filterProducts(params);
        if (productDTOS == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping(value = "/sort-asc")
    public ResponseEntity<List<ProductDTO>> sortProductsAscByField(@RequestParam String field){
        List<ProductDTO> productDTOS = productService.sortProductsAscByField(field);
        if (productDTOS == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTOS);
    }
}
