package com.codegym.jwt_spring_boot.model;

import javax.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class CartLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false)
    private Float totalPrice = 0f;

    private Timestamp addedDate;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "CartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "VariantProductId")
    private VariantProduct variantProduct;

    @ManyToOne
    @JoinColumn(name = "Order_id", nullable = true)
    private Order order;

    // Getters and Setters
}

