package com.desafio.itau.entity.mapper;

import com.desafio.itau.entity.Transaction;
import com.desafio.itau.entity.dto.TransactionRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public static Transaction toEntity(TransactionRequestDto dto) {
        return new Transaction(dto.valor(), dto.dataHora());
    }

    public static TransactionRequestDto toDto(Transaction entity) {
        return new TransactionRequestDto(entity.getValor(), entity.getDataHora());
    }
}
