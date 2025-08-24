package com.ag.simplesalessystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SaleTransactionRequest(
        @NotNull Long productId,
        @Positive int quantity,
        BigDecimal price
) {
}
