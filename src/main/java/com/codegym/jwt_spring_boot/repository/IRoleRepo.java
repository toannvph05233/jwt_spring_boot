package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepo extends CrudRepository<Role, Integer> {
}
