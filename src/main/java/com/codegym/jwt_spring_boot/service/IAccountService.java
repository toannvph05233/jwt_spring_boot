package com.codegym.jwt_spring_boot.service;

import com.codegym.jwt_spring_boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {
    User findByUsername(String username);
    User save(User user);


}
