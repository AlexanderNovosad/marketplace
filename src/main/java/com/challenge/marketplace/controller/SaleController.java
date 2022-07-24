package com.challenge.marketplace.controller;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.entity.Sale;
import com.challenge.marketplace.entity.User;
import com.challenge.marketplace.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    private final ISaleService saleService;

    @Autowired
    public SaleController(ISaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<Sale> addSale(@PathVariable(value = "userId")Long userId, @RequestParam(value = "productId")Long productId){
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.addSale(userId, productId));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Sale> getSale(@RequestParam(value = "saleId")Long saleId){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getSale(saleId));
    }

    @GetMapping(value = "/sales/products")
    public ResponseEntity<List<Product>> getAllProductsByUser(@RequestParam(value = "userId")Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getAllSalesByUser(userId));
    }

    @GetMapping(value = "/sales/users")
    public ResponseEntity<List<User>> getAllUsersByProduct(@RequestParam(value = "productId")Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getAllSalesByProduct(productId));
    }

    @GetMapping(value = "/sales")
    public ResponseEntity<List<Sale>> getAllSales(){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getAllSales());
    }

    @DeleteMapping(value = "/sales/user/")
    public ResponseEntity deleteSalesByUser(@RequestParam(value = "userId") Long userId) {
        saleService.deleteSalesByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("delete completed successfully");
    }

    @DeleteMapping(value = "/sales/product/")
    public ResponseEntity deleteSalesByProduct(@RequestParam(value = "productId") Long productId) {
        saleService.deleteSalesByProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("delete completed successfully");
    }

}
