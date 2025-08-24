package com.ag.simplesalessystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SaleTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int oldQuantity;

    @Column(nullable = false)
    private int newQuantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal oldPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal newPrice;

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "sale_transaction_id", nullable = false)
    private SaleTransaction saleTransaction;
}
