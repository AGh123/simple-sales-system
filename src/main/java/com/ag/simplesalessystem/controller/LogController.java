package com.ag.simplesalessystem.controller;

import com.ag.simplesalessystem.dto.response.LogResponse;
import com.ag.simplesalessystem.repository.SaleTransactionLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final SaleTransactionLogRepository logRepository;

    public LogController(SaleTransactionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping
    public List<LogResponse> getAllLogs() {
        return logRepository.findAll().stream()
                .map(log -> new LogResponse(
                        log.getId(),
                        log.getSaleTransaction().getId(),
                        log.getOldQuantity(),
                        log.getNewQuantity(),
                        log.getOldPrice(),
                        log.getNewPrice(),
                        log.getUpdatedAt()
                ))
                .toList();
    }
}
