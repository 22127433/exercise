package com.example.java.exercises.task6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;
    private String product_name;
    private String product_description;
    private int product_price;
    private int category_id;

    public Product() {
        product_id = 0;
        product_name = "";
        product_description = "";
        product_price = 0;
        category_id = 0;
    }

    public Product(int product_id, String product_name, String product_description, int product_price, int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.category_id = category_id;
    }

    // Getter
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public int getProduct_price() {
        return product_price;
    }

    public int getCategory_id() {
        return category_id;
    }

    // Setter
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
