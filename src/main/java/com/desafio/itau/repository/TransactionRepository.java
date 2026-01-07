package com.desafio.itau.repository;

import com.desafio.itau.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
