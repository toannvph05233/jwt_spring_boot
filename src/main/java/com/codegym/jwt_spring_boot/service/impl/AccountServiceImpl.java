package com.codegym.jwt_spring_boot.service.impl;

import com.codegym.jwt_spring_boot.model.User;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import com.codegym.jwt_spring_boot.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iAccountRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.getRoles());
    }

    @Override
    public User findByUsername(String username) {
        return iAccountRepo.findByUsername(username);
    }

    @Override
    public User save(User user) {
        User user1 = findByUsername(user.getUsername());
        if(user1 != null){
            return null;
        }
        return iAccountRepo.save(user);
    }
}
