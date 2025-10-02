package com.example.java.exercises.task7.service.interfaces;

import com.example.java.exercises.task7.dto.ListAccountResponseDTO;

import java.math.BigDecimal;

public interface TransferMoneyService {
    ListAccountResponseDTO transferMoney(int from_account_id, int to_account_id, BigDecimal amount, String description);
}
