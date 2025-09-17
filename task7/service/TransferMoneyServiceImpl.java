package com.example.java.exercises.task7.service;

import com.example.java.exercises.task7.dto.AccountResponseDTO;
import com.example.java.exercises.task7.dto.ListAccountResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferMoneyServiceImpl implements TransferMoneyService {
    private final AccountService accountService;
    private final TransactionHistoryService transactionHistoryService;

    public TransferMoneyServiceImpl(AccountService accountService, TransactionHistoryService transactionHistoryService) {
        this.accountService = accountService;
        this.transactionHistoryService = transactionHistoryService;
    }

    @Override
    @Transactional(
        isolation = Isolation.SERIALIZABLE
    )
    public ListAccountResponseDTO transferMoney(int from_account_id, int to_account_id, BigDecimal amount, String description) {
        transactionHistoryService.createTransaction(from_account_id, amount.negate(), description);
        transactionHistoryService.createTransaction(to_account_id, amount, description);

        AccountResponseDTO from_account = accountService.updateBalance(from_account_id, amount.negate());
        AccountResponseDTO to_account = accountService.updateBalance(to_account_id, amount);

        if (from_account != null && to_account != null){
            return new ListAccountResponseDTO(List.of(from_account, to_account));
        }
        return null;
    }
}