package com.desafio.itau.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDto(
        @NotNull
        @Positive
        BigDecimal valor,
        @NotNull
        @PastOrPresent
        LocalDateTime dataHora
) {
}
