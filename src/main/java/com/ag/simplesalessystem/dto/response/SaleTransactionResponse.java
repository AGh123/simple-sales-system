package com.ag.simplesalessystem.dto.response;

import java.math.BigDecimal;

public record SaleTransactionResponse(
        Long id,
        ProductResponse product,
        int quantity,
        BigDecimal price
) {
}
