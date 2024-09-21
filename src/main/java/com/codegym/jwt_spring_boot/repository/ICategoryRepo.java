package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {
}
