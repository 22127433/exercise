package com.example.java.exercises.task9.controller;

import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.service.interfaces.UploadCsvService;
import com.example.java.exercises.task9.utils.CsvParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping (value = "/upload-csv")
public class UploadCsvController {
    private final UploadCsvService uploadCsvService;

    public UploadCsvController(UploadCsvService uploadCsvService) {
        this.uploadCsvService = uploadCsvService;
    }

    @PostMapping(value = "/import-product")
    public ResponseEntity<List<ProductDTO>> importProduct(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (file.getSize() > 4.0000E+9){
            System.out.println("File is too large");
            return ResponseEntity.badRequest().build();
        }

        String filename = file.getOriginalFilename();
        if (filename == null){ return ResponseEntity.badRequest().build(); }

        String extension = filename.substring(filename.lastIndexOf("."));
        int batchSize = 10000;
        Iterator<List<ProductDTO>> itr = switch (extension){
            case "csv" -> new CsvParser(file.getInputStream(), batchSize);
            case "json" -> new CsvParser(file.getInputStream(), batchSize);
            default -> throw new IllegalArgumentException("Invalid file type");
        };

        while (itr.hasNext()){
            List<ProductDTO> list = itr.next();
            uploadCsvService.processBatch(list);
        }
        return ResponseEntity.ok().build();
    }
}
