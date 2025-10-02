package com.example.java.exercises.task9.repository;

import com.example.java.exercises.task9.entity.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Integer> {
}
