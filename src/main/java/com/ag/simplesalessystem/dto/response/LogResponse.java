package com.ag.simplesalessystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LogResponse(
        Long id,
        Long saleTransactionId,
        int oldQuantity,
        int newQuantity,
        BigDecimal oldPrice,
        BigDecimal newPrice,
        LocalDateTime updatedAt
) {
}
