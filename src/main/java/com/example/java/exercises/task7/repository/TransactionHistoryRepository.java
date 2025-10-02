package com.example.java.exercises.task7.repository;

import com.example.java.exercises.task7.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer> {
}
