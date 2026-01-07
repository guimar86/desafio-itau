package com.desafio.itau.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private UUID id = UUID.randomUUID();
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Transaction(BigDecimal valor, LocalDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }
}
