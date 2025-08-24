package com.ag.simplesalessystem.service;

import com.ag.simplesalessystem.dto.request.ProductRequest;
import com.ag.simplesalessystem.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, ProductRequest request);
}
