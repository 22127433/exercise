package com.example.java.exercises.task7.service.interfaces;

import com.example.java.exercises.task7.dto.AccountResponseDTO;

import java.math.BigDecimal;

public interface AccountService {
    AccountResponseDTO updateBalance(int account_id, BigDecimal balance);
}
