package com.challenge.marketplace.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "Product")
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(nullable = false)
    @Size(min = 1, message = "Product should have at least 1 character")
    private String name;

    @Column(nullable = false)
    @Digits(integer = 20,fraction = 2)
    private BigDecimal price;

    public Product(){}

    public Product(@Size(min = 1) String name, @Digits(integer = 20, fraction = 2) BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
