package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order, Integer> {
}
