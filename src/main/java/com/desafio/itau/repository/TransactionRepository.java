package com.desafio.itau.repository;

import com.desafio.itau.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new java.util.ArrayList<>();

    public List<Transaction> findAll() {
        return transactions;
    }

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deleteAll() {
        transactions.clear();
    }

    public List<Transaction> findDataHoraAfter(long minutes) {
        LocalDateTime from = LocalDateTime.now().minusMinutes(minutes);

        return transactions.stream()
                .filter(t -> t.getDataHora() != null)
                .filter(t -> t.getDataHora().isAfter(from))
                .collect(Collectors.toList());
    }
}
