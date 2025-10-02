package com.example.java.controller;

import com.example.java.entity.UserTable;
import com.example.java.service.UserTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserTableController {
    private final UserTableService userTableService;

    public UserTableController(UserTableService userTableService){
        this.userTableService = userTableService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserTable>> getAllUserTables() {
        return ResponseEntity.ok(userTableService.getAllUserTables());
    }
}
