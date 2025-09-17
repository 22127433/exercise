package com.example.java.exercises.task7.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface TransactionHistoryService {
    void createTransaction(int account_id, BigDecimal amount, String description);
}
