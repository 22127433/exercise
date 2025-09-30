package com.example.java.exercises.task9.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "CUSTOMERS")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;

    @OneToMany (mappedBy = "customer")
    private List<Order> orders;

    @OneToMany (mappedBy = "customer")
    private List<PaymentAccount> paymentAccounts;
}
