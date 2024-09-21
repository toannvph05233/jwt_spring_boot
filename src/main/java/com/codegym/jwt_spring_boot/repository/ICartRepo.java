package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepo extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserId(Integer userId);

}
