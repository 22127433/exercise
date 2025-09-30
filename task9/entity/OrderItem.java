package com.example.java.exercises.task9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDERSITEM")
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;
}
