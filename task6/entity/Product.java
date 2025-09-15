package com.example.java.exercises.task6.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String product_name;
    private String product_description;
    private int product_price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
//        productId = 0;
        product_name = "";
        product_description = "";
        product_price = 0;
        category = new Category();
    }

    public Product(int productId){
        this.productId = productId;
    }

    public Product(int productId, String product_name, String product_description, int product_price, Category category) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.category = category;
    }

    // Getter
    public int getProductId() {
        return productId;
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

    public Category getCategory() {
        return category;
    }

    // Setter
    public void setProductId(int productId) {
        this.productId = productId;
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

    public void setCategory(Category category) {
        this.category = category;
    }
}
