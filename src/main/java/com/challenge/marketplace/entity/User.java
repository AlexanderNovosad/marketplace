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
@Entity (name = "User")
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    @Size(min = 1, message = "First Name should have at least 1 character")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, message = "Last Name should have at least 1 character")
    private String lastName;

    @Column(nullable = false)
    @Digits(integer = 20,fraction = 2)
    private BigDecimal amountOfMoney;

    public User(){}

    public User(@Size(min = 5) String firstName, @Size(min = 5) String lastName, @Digits(integer = 20,fraction = 2) BigDecimal amountOfMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfMoney = amountOfMoney;
    }
}
