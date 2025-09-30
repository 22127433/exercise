package com.example.java.exercises.task9.service.interfaces;

import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.dto.product.ProductParamRequest;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(int id);
    ProductDTO createProduct(ProductModifyDTO productModifyDTO);
    void updateProduct(int id, ProductModifyDTO productModifyDTO);
    void deleteProductById(int id);
    List<ProductDTO> getProductsLikeName(String name);
    List<ProductDTO> sortProductsAscByField(String field);
    List<ProductDTO> filterProducts(ProductParamRequest params);
    void adjustInventoryQuantity(int productId, int quantity, boolean isIncrease);
}
