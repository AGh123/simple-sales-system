package com.ag.simplesalessystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SaleTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oldQuantity;
    private int newQuantity;

    private Double oldPrice;
    private Double newPrice;

    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "sale_transaction_id", nullable = false)
    private SaleTransaction saleTransaction;
}
