package com.codegym.jwt_spring_boot.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "Userid", nullable = false)
    private User user;
}

