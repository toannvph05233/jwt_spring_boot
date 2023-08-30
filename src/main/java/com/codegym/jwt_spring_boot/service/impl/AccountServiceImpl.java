package com.codegym.jwt_spring_boot.service.impl;

import com.codegym.jwt_spring_boot.model.Account;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import com.codegym.jwt_spring_boot.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.findByUsername(username);
        return new User(username,account.getPassword(),account.getRoles());
    }

    @Override
    public Account findByUsername(String username) {
        return iAccountRepo.findByUsername(username);
    }
}
