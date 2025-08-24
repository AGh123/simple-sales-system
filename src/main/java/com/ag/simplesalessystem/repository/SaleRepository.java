package com.ag.simplesalessystem.repository;

import com.ag.simplesalessystem.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
