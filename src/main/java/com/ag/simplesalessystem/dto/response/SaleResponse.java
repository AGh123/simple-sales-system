package com.ag.simplesalessystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record SaleResponse(
        Long id,
        LocalDate date,
        ClientResponse client,
        BigDecimal total,
        List<SaleTransactionResponse> transactions
) {
}
