package com.ag.simplesalessystem.repository;

import com.ag.simplesalessystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByMobile(String mobile); // to prevent duplicate mobiles
}
