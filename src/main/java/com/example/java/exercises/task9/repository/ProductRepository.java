package com.example.java.exercises.task9.repository;

import com.example.java.exercises.task9.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select p from Product p join fetch p.inventory where p.id =:id")
    Product findProductById(@Param("id") int id);

    @Query("select p from Product p join fetch p.inventory order by :field asc")
    List<Product> findAllProductsByField(@Param("field") String field);
    List<Product> findByNameContaining(String name);
    List<Product> findProductsByPriceBetweenOrderByPriceAsc(BigDecimal price, BigDecimal price2);
    List<Product> findProductsByPriceBetweenOrderByPriceDesc(BigDecimal price, BigDecimal price2);
}
