package com.challenge.marketplace.service.Impl;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.entity.Sale;
import com.challenge.marketplace.entity.User;
import com.challenge.marketplace.exception.IllegalArithmeticOperationException;
import com.challenge.marketplace.repo.ProductRepository;
import com.challenge.marketplace.repo.SaleRepository;
import com.challenge.marketplace.repo.UserRepository;
import com.challenge.marketplace.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, UserRepository userRepository, ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Sale addSale(Long userId, Long productId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        purchase(user, product.getPrice());

        return saleRepository.saveAndFlush(new Sale(user, product));
    }

    private void purchase (User user, BigDecimal price){
        if(user.getAmountOfMoney().subtract(price).doubleValue() < 0){
            throw new IllegalArithmeticOperationException("The user's amount of money is less than the price of product");
        }
        else{
            user.setAmountOfMoney(user.getAmountOfMoney().subtract(price));
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public Sale getSale(Long saleId){
        return saleRepository.findById(saleId).orElseThrow(() -> new EntityNotFoundException("Sale not found"));
    }

    @Override
    public List<Product> getAllSalesByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Sale> sales = saleRepository.findAllByUser(user);
        if (sales.isEmpty()) {
            return null;
        }
        List<Product> products = new ArrayList<>();
        for(Sale sale : sales){
            products.add(sale.getProduct());
        }
        return products;
    }

    @Override
    public List<User> getAllSalesByProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        List<Sale> sales = saleRepository.findAllByProduct(product);
        if (sales.isEmpty()) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for(Sale sale : sales){
            users.add(sale.getUser());
        }
        return users;
    }

    @Override
    public void deleteSalesByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if(saleRepository.findAllByUser(user).isEmpty()){
            throw new EntityNotFoundException("This user hasn't purchased anything");
        }
        else {
            saleRepository.deleteAllByUser(user);
        }
        saleRepository.deleteAllByUser(user);
    }

    @Override
    public void deleteSalesByProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if(saleRepository.findAllByProduct(product).isEmpty()){
            throw new EntityNotFoundException("This product hasn't been sold");
        }
        else {
            saleRepository.deleteAllByProduct(product);
        }
    }

    @Override
    public List<Sale> getAllSales(){
        List<Sale> allSales = saleRepository.findAll();
        if (allSales.isEmpty()) {
            throw new EntityNotFoundException("No sales found in DB");
        }
        return allSales;
    }
}
