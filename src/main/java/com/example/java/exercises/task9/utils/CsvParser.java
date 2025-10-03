package com.example.java.exercises.task9.utils;

import com.example.java.exercises.task9.dto.product.ProductDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvParser implements Iterator<List<ProductDTO>> {
    private final BufferedReader reader;
    private final int batchSize;
    private String line;

    public CsvParser(InputStream inputStream, int batchSize) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.batchSize = batchSize;
        this.reader.readLine(); //skip header
        this.line = this.reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return line != null;
    }

    @Override
    public List<ProductDTO> next() {
        List<ProductDTO> list = new ArrayList<>();
        while (line != null & list.size() < batchSize) {
            try {
                String[] fields = line.trim().split(",");
                list.add(ProductDTO.builder()
                        .id(Integer.parseInt(fields[0]))
                        .name(fields[1])
                        .price(Double.parseDouble(fields[2]))
                        .quantity(Integer.parseInt(fields[3]))
                        .build());
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
