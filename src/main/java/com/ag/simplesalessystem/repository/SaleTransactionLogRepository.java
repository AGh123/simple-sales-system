package com.ag.simplesalessystem.repository;

import com.ag.simplesalessystem.entity.SaleTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionLogRepository extends JpaRepository<SaleTransactionLog, Long> {
}
