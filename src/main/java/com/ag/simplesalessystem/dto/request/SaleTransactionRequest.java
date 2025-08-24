package com.ag.simplesalessystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleTransactionRequest(
        @NotNull Long productId,
        @Positive int quantity
) {
}
