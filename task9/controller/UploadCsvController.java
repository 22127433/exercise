package com.example.java.exercises.task9.controller;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.service.interfaces.UploadCsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping (value = "/upload-csv")
public class UploadCsvController {
    private final UploadCsvService uploadCsvService;

    public UploadCsvController(UploadCsvService uploadCsvService) {
        this.uploadCsvService = uploadCsvService;
    }

    @PostMapping(value = "/import-product")
    public ResponseEntity<List<ProductDTO>> importProduct(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".csv")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        try {
            uploadCsvService.importProduct(file);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
