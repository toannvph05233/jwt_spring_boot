package com.codegym.jwt_spring_boot.model.dto;

import com.codegym.jwt_spring_boot.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountToken {
    private int id;
    private String username;
    private String token;
    private List<Role> roles;
}
