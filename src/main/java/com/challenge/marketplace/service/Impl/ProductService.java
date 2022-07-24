package com.challenge.marketplace.service.Impl;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.repo.ProductRepository;
import com.challenge.marketplace.repo.SaleRepository;
import com.challenge.marketplace.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SaleRepository saleRepository){
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Product addProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product getProduct(Long productId){
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        saleRepository.deleteAllByProduct(product);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        if (allProducts.isEmpty()) {
            throw new EntityNotFoundException("No products found in DB");
        }
        return allProducts;
    }
}
