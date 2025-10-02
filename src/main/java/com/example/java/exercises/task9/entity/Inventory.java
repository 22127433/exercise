package com.example.java.exercises.task9.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "QUANTITY")
    private int quantity;
    @OneToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;
}