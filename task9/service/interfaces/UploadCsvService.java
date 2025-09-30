package com.example.java.exercises.task9.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface UploadCsvService {
    void importProduct(MultipartFile file);
}
