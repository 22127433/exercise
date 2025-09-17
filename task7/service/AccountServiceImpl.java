package com.example.java.exercises.task7.service;

import com.example.java.exercises.task7.dto.AccountResponseDTO;
import com.example.java.exercises.task7.entity.Account;
import com.example.java.exercises.task7.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AccountResponseDTO updateBalance(int account_id, BigDecimal balance) {
        Optional<Account> account = accountRepository.findById(account_id);
        if (account.isPresent()) {
            BigDecimal currentBalance = account.get().getBalance();
            if (currentBalance.compareTo(balance) > 0) {
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
}
