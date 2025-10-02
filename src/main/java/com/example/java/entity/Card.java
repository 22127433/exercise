package com.example.java.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Card {
    private Long card_id;
    private String name;
    private String description;
    private Long board_id;
    private String card_progress;
    private Boolean status;
    private LocalDateTime due_date;
    private LocalDateTime complete_at;
    private LocalDateTime create_at;

    // getter
    public Long getCard_id() {
        return card_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public String getCard_progress() {
        return card_progress;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public LocalDateTime getComplete_at() {
        return complete_at;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    // setter
    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public void setCard_progress(String card_progress) {
        this.card_progress = card_progress;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public void setComplete_at(LocalDateTime complete_at) {
        this.complete_at = complete_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }
}
