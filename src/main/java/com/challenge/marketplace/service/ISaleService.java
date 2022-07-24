package com.challenge.marketplace.service;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.entity.Sale;
import com.challenge.marketplace.entity.User;

import java.util.List;

public interface ISaleService {
    Sale addSale(Long userId, Long productId);

    Sale getSale(Long saleId);

    List<Product> getAllSalesByUser(Long userId);

    List<User> getAllSalesByProduct(Long productId);

    void deleteSalesByUser(Long userId);

    void deleteSalesByProduct(Long productId);

    List<Sale> getAllSales();
}
