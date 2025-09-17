package com.example.java.exercises.task7.dto;

public record TransferMoneyDTO(int from_account_id, int to_account_id, String amount, String description) {
}
