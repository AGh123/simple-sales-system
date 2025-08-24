package com.ag.simplesalessystem.controller;

import com.ag.simplesalessystem.dto.request.CreateSaleRequest;
import com.ag.simplesalessystem.dto.response.SaleResponse;
import com.ag.simplesalessystem.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<SaleResponse> getSales() {
        return saleService.getAllSales();
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(
            @Valid @RequestBody CreateSaleRequest request) {
        return ResponseEntity.ok(saleService.createSale(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody CreateSaleRequest request) {
        return ResponseEntity.ok(saleService.updateSale(id, request));
    }
}
