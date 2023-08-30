package com.codegym.jwt_spring_boot.service;

import com.codegym.jwt_spring_boot.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {
    Account findByUsername(String username);

}
