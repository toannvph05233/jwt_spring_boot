package com.codegym.jwt_spring_boot.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class VariantProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String size;

    @Column(length = 100)
    private String model;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(name = "Product_id", nullable = false)
    private Product product;

}

