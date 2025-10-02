package com.example.java.exercises.task9.service.interfaces;

import java.math.BigDecimal;

public interface PaymentService {
    void charge(int paymentAccountId, BigDecimal amount, boolean success);
}
