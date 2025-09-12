package com.example.java.exercises.task6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_id;
    private String category_name;
    private String category_description;

    public Category() {
        this.category_id = 0;
        this.category_name = "";
        this.category_description = "";
    }

    public Category(int category_id, String category_name, String category_description) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_description = category_description;
    }

    // Getter
    public int getCategory_id() {
        return category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public String getCategory_description() {
        return category_description;
    }

    // Setter
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }
}
