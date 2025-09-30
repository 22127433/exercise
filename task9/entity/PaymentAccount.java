package com.example.java.exercises.task9.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "PAYMENTACCOUNT")
public class PaymentAccount extends BaseEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BALANCE")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;
}
