package com.example.java.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class UserTable {
    private Long id;
    private String hoten;
    private Date ngaysinh;
    private String diachi;
    private String gioitinh;

    // getter
    public Long getId() {
        return this.id;
    }

    public String getHoten() {
        return this.hoten;
    }

    public Date getNgaysinh() {
        return this.ngaysinh;
    }

    public String getDiachi() {
        return this.diachi;
    }

    public String getGioitinh() {
        return this.gioitinh;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
}
