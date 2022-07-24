package com.challenge.marketplace.service;

import com.challenge.marketplace.entity.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);

    Product getProduct(Long productId);

    void deleteProduct(Long productId);

    List<Product> getAllProducts();
}
