package com.example.java.exercises.task7.controller;

import com.example.java.exercises.task7.dto.ListAccountResponseDTO;
import com.example.java.exercises.task7.dto.TransferMoneyDTO;
import com.example.java.exercises.task7.service.interfaces.TransferMoneyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/transaction")
public class TransferMoneyController {
    private final TransferMoneyService transferMoneyService;

    public TransferMoneyController(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }

    @PostMapping(value = "/")
    public ListAccountResponseDTO transferMoney(@RequestBody TransferMoneyDTO transferMoneyDTO) {
        return transferMoneyService.transferMoney(transferMoneyDTO.from_account_id(), transferMoneyDTO.to_account_id(), new BigDecimal(transferMoneyDTO.amount()), transferMoneyDTO.description());
    }
}
