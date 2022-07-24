package com.challenge.marketplace.controller;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService productService;

    @Autowired
    ProductController(IProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @GetMapping(value = "/")
    public ResponseEntity<Product> getProduct(@RequestParam(value = "productId") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity deleteProduct(@RequestParam(value = "productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("delete completed successfully");
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
