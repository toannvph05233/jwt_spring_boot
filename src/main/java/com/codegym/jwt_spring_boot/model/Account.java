package com.codegym.jwt_spring_boot.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
