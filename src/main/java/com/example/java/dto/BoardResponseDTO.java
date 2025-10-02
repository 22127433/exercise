package com.example.java.dto;

import java.time.LocalDateTime;

public record BoardResponseDTO(Long board_id, String name, Long user_id, LocalDateTime create_at) {}
