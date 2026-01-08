package com.desafio.itau.controller;

import com.desafio.itau.entity.dto.TransactionRequestDto;
import com.desafio.itau.entity.mapper.TransactionMapper;
import com.desafio.itau.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Tag(name = "Transaction", description = "Endpoint for transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create a transaction", description = "Create a transaction")
    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequestDto transaction) {

        transactionService.save(TransactionMapper.toEntity(transaction));
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Retrieve all transactions", description = "Get all transactions")
    public ResponseEntity<List<TransactionRequestDto>> findAll() {
        var transactions = transactionService.findAll()
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Delete all transactions", description = "Delete all transactions")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        transactionService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
