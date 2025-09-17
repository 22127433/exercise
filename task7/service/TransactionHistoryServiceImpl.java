package com.example.java.exercises.task7.service;

import com.example.java.exercises.task7.entity.Account;
import com.example.java.exercises.task7.entity.TransactionHistory;
import com.example.java.exercises.task7.repository.AccountRepository;
import com.example.java.exercises.task7.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AccountRepository accountRepository;

    public TransactionHistoryServiceImpl(TransactionHistoryRepository transactionHistoryRepository, AccountRepository accountRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(
//            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED
    )
    public void createTransaction(int account_id, BigDecimal amount, String description) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAmount(amount);
        transactionHistory.setDescription(description);
        Optional<Account> account = accountRepository.findById(account_id);
        if (account.isPresent()) {
            transactionHistory.setAccount(account.get());
            transactionHistoryRepository.save(transactionHistory);
        }
    }
}
