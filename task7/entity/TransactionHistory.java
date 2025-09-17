package com.example.java.exercises.task7.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private String description;
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public TransactionHistory() {}
    public TransactionHistory(int id, BigDecimal amount, String description, Account account) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public String  getDescription() {
        return description;
    }
    public Account getAccount() {
        return account;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
