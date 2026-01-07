package com.desafio.itau.controller;

import com.desafio.itau.entity.dto.TransactionRequestDto;
import com.desafio.itau.entity.mapper.TransactionMapper;
import com.desafio.itau.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequestDto transaction) {

        transactionService.save(TransactionMapper.toEntity(transaction));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionRequestDto>> findAll() {
        var transactions = transactionService.findAll()
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
        return ResponseEntity.ok(transactions);
    }

}
