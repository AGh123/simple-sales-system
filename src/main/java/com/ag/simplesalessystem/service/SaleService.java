package com.ag.simplesalessystem.service;

import com.ag.simplesalessystem.dto.request.CreateSaleRequest;
import com.ag.simplesalessystem.dto.response.SaleResponse;

import java.util.List;

public interface SaleService {
    List<SaleResponse> getAllSales();

    SaleResponse createSale(CreateSaleRequest request);

    SaleResponse updateSale(Long id, CreateSaleRequest request);
}
