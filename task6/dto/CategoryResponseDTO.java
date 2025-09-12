package com.example.java.exercises.task6.dto;

import java.util.List;

public record CategoryResponseDTO (int category_id, String category_name, String category_description, List<ProductDTO> products) {}
