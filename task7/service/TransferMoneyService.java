package com.example.java.exercises.task7.service;

import com.example.java.exercises.task7.dto.AccountResponseDTO;
import com.example.java.exercises.task7.dto.ListAccountResponseDTO;

import java.math.BigDecimal;

public interface TransferMoneyService {
    void createTransaction(int account_id, BigDecimal amount, String description);
    AccountResponseDTO updateBalance(int account_id, BigDecimal balance);
    ListAccountResponseDTO transferMoney(int from_account_id, int to_account_id, BigDecimal amount, String description);
}
