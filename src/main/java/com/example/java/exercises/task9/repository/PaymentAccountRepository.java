package com.example.java.exercises.task9.repository;

import com.example.java.exercises.task9.entity.PaymentAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Integer> {
    @Query("SELECT p FROM PaymentAccount p WHERE p.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PaymentAccount> findByIdWithLock(@Param("id") int id);
}
