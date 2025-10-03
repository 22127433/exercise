package com.example.java.exercises.task9.service.interfaces;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface UploadCsvService {
    void processBatch(List<ProductDTO> productDTOs);
}
