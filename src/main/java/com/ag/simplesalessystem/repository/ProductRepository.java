package com.ag.simplesalessystem.repository;

import com.ag.simplesalessystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}