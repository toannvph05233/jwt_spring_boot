package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Integer> {
}
