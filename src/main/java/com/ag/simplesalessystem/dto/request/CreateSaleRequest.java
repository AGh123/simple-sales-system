package com.ag.simplesalessystem.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateSaleRequest(
        @NotNull Long clientId,
        @NotNull List<SaleTransactionRequest> transactions
) {
}
