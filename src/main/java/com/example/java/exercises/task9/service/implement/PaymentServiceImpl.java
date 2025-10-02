package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.entity.PaymentAccount;
import com.example.java.exercises.task9.repository.PaymentAccountRepository;
import com.example.java.exercises.task9.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentAccountRepository paymentAccountRepository;

    public PaymentServiceImpl(PaymentAccountRepository paymentAccountRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
    }

    @Override
    @Transactional(
            propagation = Propagation.MANDATORY,
            isolation = Isolation.SERIALIZABLE
    )
    public void charge(int paymentAccountId, BigDecimal amount, boolean success) {
        PaymentAccount paymentAccount = paymentAccountRepository.findById(paymentAccountId)
                .orElseThrow(() -> new RuntimeException("PaymentAccount not found"));

        if (paymentAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));
        if (!success){
            throw new RuntimeException("Payment failed");
        }
    }
}
