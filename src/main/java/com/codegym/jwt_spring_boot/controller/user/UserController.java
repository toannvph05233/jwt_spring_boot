package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.User;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IAccountRepo iUserRepo;

    // 1. Get all information of the currently logged-in user
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = iUserRepo.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 2. Update information of the currently logged-in user
    @PutMapping("/me/update")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User updatedUser, Authentication authentication) {
        String username = authentication.getName();
        User user = iUserRepo.findByUsername(username);
        if (user != null) {
            user.setFullname(updatedUser.getFullname());
            user.setEmail(updatedUser.getEmail());
            // Update other fields as necessary
            User savedUser = iUserRepo.save(user);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

