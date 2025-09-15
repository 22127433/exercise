package com.example.java.exercises.task6.mapper;

import com.example.java.exercises.task6.dto.ProductCreateDTO;
import com.example.java.exercises.task6.dto.ProductDTO;
import com.example.java.exercises.task6.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productCreateDTOToProduct(ProductCreateDTO productCreateDTO){
        Product product = new Product();
        product.setProduct_name(productCreateDTO.product_name());
        product.setProduct_description(productCreateDTO.product_description());
        product.setProduct_price(productCreateDTO.product_price());
        product.setCategory(null);
        return product;
    }
    public ProductDTO productToProductDTO(Product product){
        return new ProductDTO (
                product.getProductId(),
                product.getProduct_name(),
                product.getProduct_description(),
                product.getProduct_price(),
                product.getCategory().getCategory_id()
        );
    }

    public Product productDTOToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setProductId(productDTO.productId());
        product.setProduct_name(productDTO.product_name());
        product.setProduct_description(productDTO.product_description());
        product.setProduct_price(productDTO.product_price());
        product.setCategory(null);
        return product;
    }
}
