package com.example.java.exercises.task7.service;

import com.example.java.exercises.task7.dto.AccountResponseDTO;
import com.example.java.exercises.task7.dto.ListAccountResponseDTO;
import com.example.java.exercises.task7.entity.Account;
import com.example.java.exercises.task7.entity.TransactionHistory;
import com.example.java.exercises.task7.repository.AccountRepository;
import com.example.java.exercises.task7.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferMoneyServiceImpl implements TransferMoneyService {
    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransferMoneyServiceImpl(AccountRepository accountRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.accountRepository = accountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    @Override
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

    @Override
    public AccountResponseDTO updateBalance(int account_id, BigDecimal balance) {
        Optional<Account> account = accountRepository.findById(account_id);
        if (account.isPresent()) {
            BigDecimal currentBalance = account.get().getBalance();
            if (currentBalance.compareTo(balance) < 0) {
                throw new IllegalArgumentException("Tài khoản không đủ tiền");
            }
            currentBalance = currentBalance.add(balance);
            account.get().setBalance(currentBalance);
            accountRepository.save(account.get());

            return new AccountResponseDTO(
                    account.get().getId(),
                    account.get().getOwner_name(),
                    account.get().getBalance().toString()
            );
        }
        return null;
    }

    @Override
    @Transactional(
        isolation = Isolation.SERIALIZABLE
    )
    public ListAccountResponseDTO transferMoney(int from_account_id, int to_account_id, BigDecimal amount, String description) {
        createTransaction(from_account_id, amount.negate(), description);
        AccountResponseDTO from_account = updateBalance(from_account_id, amount.negate());
        createTransaction(to_account_id, amount, description);
        AccountResponseDTO to_account = updateBalance(to_account_id, amount);

        if (from_account != null && to_account != null){
            return new ListAccountResponseDTO(List.of(from_account, to_account));
        }
        return null;
    }
}