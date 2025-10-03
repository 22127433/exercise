package com.example.java.exercises.task9.repository;

import com.example.java.exercises.task9.entity.Inventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Inventory> findByProductId(int id);

    @Modifying
    @Query("UPDATE Inventory i SET i.quantity = i.quantity + :quantity WHERE i.id = :id")
    void increaseStock(@Param("id") int id,  @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE Inventory i SET i.quantity = i.quantity - :quantity WHERE i.id = :id")
    void  decreaseStock(@Param("id") int id,  @Param("quantity") int quantity);
}
