package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
import com.example.java.exercises.task9.service.interfaces.ProductService;
import com.example.java.exercises.task9.service.interfaces.UploadCsvService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class UploadCsvServiceImpl implements UploadCsvService {
    private final ProductService productService;

    public UploadCsvServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @Transactional
    public void importProduct(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            reader.lines().skip(1)
                    .forEach(line -> {
                        String[] fields = line.trim().split(",");
                        if (fields.length == 4) {
                            int  id = Integer.parseInt(fields[0]);
                            int quantity = Integer.parseInt(fields[3]);

                            ProductModifyDTO productModifyDTO = new ProductModifyDTO(
                                    fields[1],
                                    Double.parseDouble(fields[2]),
                                    quantity
                            );
                            ProductDTO productDTO = productService.getProductById(id);
                            if (productDTO == null) {
                                try {
                                    productService.createProduct(productModifyDTO);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                quantity += productDTO.getQuantity();
//                                if (quantity < 0) {
//                                    throw new RuntimeException("Quantity cannot be less than 0");
//                                }
                                productModifyDTO.setQuantity(quantity);
                                try {
                                    productService.updateProduct(id, productModifyDTO);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
