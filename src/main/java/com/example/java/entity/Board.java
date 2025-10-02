package com.example.java.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


public class Board {
    private Long board_id;
    private String name;
    private Long user_id;
    private LocalDateTime create_at;

    public Board(Long board_id, String name, Long user_id, LocalDateTime create_at) {
        this.board_id = board_id;
        this.name = name;
        this.user_id = user_id;
        this.create_at = create_at;
    }

    // getter
    public Long getBoardId() {
        return board_id;
    }
    public String getName() {
        return name;
    }
    public Long getUser_id() {
        return user_id;
    }
    public LocalDateTime getCreate_at() {
        return create_at;
    }

    // setter
    public void setBoardId(Long board_id) {
        this.board_id = board_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setuser_id(Long user_id) {
        this.user_id = user_id;
    }
    public void setcreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }
}
