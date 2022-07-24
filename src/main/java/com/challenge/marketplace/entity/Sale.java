package com.challenge.marketplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "Sale")
@Table(name = "sales")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public Sale() {
    }

    public Sale(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
