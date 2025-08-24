package com.ag.simplesalessystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price,
        LocalDate createdAt
) {
}
