package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVariantProductRepo extends JpaRepository<VariantProduct,Integer> {
    List<VariantProduct> findAllByProductId(int id);
}
