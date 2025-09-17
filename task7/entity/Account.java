package com.example.java.exercises.task7.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String owner_name;
    private BigDecimal balance;
    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> transactions;

    public Account() {
        balance = BigDecimal.ZERO;
        transactions = new ArrayList<>();
    }

    public Account(int id, BigDecimal balance) {
        if (BigDecimal.ZERO.compareTo(balance) <= 0) {
            throw new IllegalArgumentException("balance must be greater than zero");
        }
        this.id = id;
        this.balance = balance;
    }

    public Account(String owner_name, BigDecimal balance, List<TransactionHistory> transactions) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.owner_name = owner_name;
        this.balance = balance;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }
    public String getOwner_name() {
        return owner_name;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public List<TransactionHistory> getTransactions() {
        return transactions;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    public void setBalance(BigDecimal balance) {
        if  (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }
    public void setTransactions(List<TransactionHistory> transactions) {
        this.transactions = transactions;
    }
}
