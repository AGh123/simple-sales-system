package com.ag.simplesalessystem.repository;

import com.ag.simplesalessystem.entity.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
}
