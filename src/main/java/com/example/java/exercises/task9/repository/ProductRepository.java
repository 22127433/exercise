package com.example.java.exercises.task9.repository;

import com.example.java.exercises.task9.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContaining(String name);
    List<Product> findProductsByPriceBetweenOrderByPriceAsc(BigDecimal price, BigDecimal price2);
    List<Product> findProductsByPriceBetweenOrderByPriceDesc(BigDecimal price, BigDecimal price2);
}
