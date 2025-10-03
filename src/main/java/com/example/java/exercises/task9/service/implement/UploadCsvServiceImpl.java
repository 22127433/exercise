package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
import com.example.java.exercises.task9.service.interfaces.ProductService;
import com.example.java.exercises.task9.service.interfaces.UploadCsvService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UploadCsvServiceImpl implements UploadCsvService {
    private final ProductService productService;

    public UploadCsvServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processBatch(List<ProductDTO> productDTOs){
        for (ProductDTO product : productDTOs){
            int productId = product.getId();
            ProductModifyDTO productModifyDTO = new ProductModifyDTO(
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity()
            );
            ProductDTO productDTO1 = productService.getProductById(productId);
            if ( productDTO1 == null) {
                productService.createProduct(productModifyDTO);
            }
            else {
                productService.adjustInventoryQuantity(productId, productModifyDTO.getQuantity(),true);
                productService.updateProduct(productId, productModifyDTO);
            }
        }
    }
}
