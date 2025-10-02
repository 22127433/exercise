package com.example.java.controller;

import com.example.java.exercises.task9.entity.Product;

import java.math.BigDecimal;

public class CardController {


    public void a (){
        Product p = Product.builder()
                .price(BigDecimal.valueOf(5))
                .id(1).build();
        p.setName("a");
        p.setPrice(BigDecimal.valueOf(5));

    }
}
