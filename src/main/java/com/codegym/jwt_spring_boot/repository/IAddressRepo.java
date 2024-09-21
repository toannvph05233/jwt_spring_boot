package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAddressRepo extends JpaRepository<Address, Integer> {
    List<Address> findByUserId(Integer userId);

}
