package com.ag.simplesalessystem.service;

import com.ag.simplesalessystem.dto.request.CreateSaleRequest;
import com.ag.simplesalessystem.dto.request.SaleTransactionRequest;
import com.ag.simplesalessystem.dto.response.ClientResponse;
import com.ag.simplesalessystem.dto.response.ProductResponse;
import com.ag.simplesalessystem.dto.response.SaleResponse;
import com.ag.simplesalessystem.dto.response.SaleTransactionResponse;
import com.ag.simplesalessystem.entity.*;
import com.ag.simplesalessystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleTransactionRepository transactionRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SaleTransactionLogRepository logRepository;

    public SaleServiceImpl(SaleRepository saleRepository,
                           SaleTransactionRepository transactionRepository,
                           ClientRepository clientRepository,
                           ProductRepository productRepository,
                           SaleTransactionLogRepository logRepository) {
        this.saleRepository = saleRepository;
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.logRepository = logRepository;
    }

    @Override
    public List<SaleResponse> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> mapToResponse(sale, calculateTotal(sale)))
                .toList();
    }

    @Override
    public SaleResponse createSale(CreateSaleRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Sale sale = new Sale();
        sale.setClient(client);

        Sale savedSale = saleRepository.save(sale);

        BigDecimal total = saveTransactions(savedSale, request.transactions(), false);

        return mapToResponse(savedSale, total);
    }

    @Override
    public SaleResponse updateSale(Long id, CreateSaleRequest request) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        // Don’t delete all transactions → update existing ones and log changes
        BigDecimal total = saveTransactions(sale, request.transactions(), true);

        return mapToResponse(sale, total);
    }
    
    private BigDecimal saveTransactions(Sale sale, List<SaleTransactionRequest> requests, boolean isUpdate) {
        BigDecimal total = BigDecimal.ZERO;

        for (SaleTransactionRequest tReq : requests) {
            Product product = productRepository.findById(tReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Decide price: take from request if provided, else from product
            BigDecimal finalPrice = (tReq.price() != null) ? tReq.price() : product.getPrice();

            SaleTransaction existing = sale.getTransactions().stream()
                    .filter(tx -> tx.getProduct().getId().equals(product.getId()))
                    .findFirst()
                    .orElse(null);

            if (existing != null && isUpdate) {
                // log old values
                SaleTransactionLog log = new SaleTransactionLog();
                log.setSaleTransaction(existing);
                log.setOldQuantity(existing.getQuantity());
                log.setNewQuantity(tReq.quantity());
                log.setOldPrice(existing.getPrice());
                log.setNewPrice(finalPrice);
                log.setUpdatedAt(java.time.LocalDateTime.now());

                logRepository.save(log);

                // update values
                existing.setQuantity(tReq.quantity());
                existing.setPrice(finalPrice);

                transactionRepository.save(existing);
            } else if (existing == null) {
                // new transaction
                SaleTransaction transaction = new SaleTransaction();
                transaction.setSale(sale);
                transaction.setProduct(product);
                transaction.setQuantity(tReq.quantity());
                transaction.setPrice(finalPrice);

                transactionRepository.save(transaction);
                sale.getTransactions().add(transaction);
            }

            total = total.add(finalPrice.multiply(BigDecimal.valueOf(tReq.quantity())));
        }

        return total;
    }

    private BigDecimal calculateTotal(Sale sale) {
        return sale.getTransactions().stream()
                .map(t -> t.getPrice().multiply(BigDecimal.valueOf(t.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private SaleResponse mapToResponse(Sale sale, BigDecimal total) {
        List<SaleTransactionResponse> txResponses = sale.getTransactions()
                .stream()
                .map(t -> new SaleTransactionResponse(
                        t.getId(),
                        new ProductResponse(
                                t.getProduct().getId(),
                                t.getProduct().getName(),
                                t.getProduct().getDescription(),
                                t.getProduct().getCategory(),
                                t.getProduct().getPrice(),
                                t.getProduct().getCreatedAt()
                        ),
                        t.getQuantity(),
                        t.getPrice()
                ))
                .toList();

        return new SaleResponse(
                sale.getId(),
                sale.getDate(),
                new ClientResponse(
                        sale.getClient().getId(),
                        sale.getClient().getFirstName(),
                        sale.getClient().getLastName(),
                        sale.getClient().getMobile()
                ),
                total,
                txResponses
        );
    }
}
