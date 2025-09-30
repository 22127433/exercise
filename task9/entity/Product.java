package com.example.java.exercises.task9.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}
