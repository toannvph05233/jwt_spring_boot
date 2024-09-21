package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.Address;
import com.codegym.jwt_spring_boot.model.User;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import com.codegym.jwt_spring_boot.repository.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddressRepo iAddressRepo;

    @Autowired
    private IAccountRepo iUserRepo;

    // 1. Get all addresses for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAllAddressesByUserId(@PathVariable Integer userId) {
        Optional<User> user = iUserRepo.findById(userId);
        if (user.isPresent()) {
            List<Address> addresses = iAddressRepo.findByUserId(userId);
            return ResponseEntity.ok(addresses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 2. Add a new address for a specific user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Address> addAddress(@PathVariable Integer userId, @RequestBody Address newAddress) {
        Optional<User> user = iUserRepo.findById(userId);
        if (user.isPresent()) {
            newAddress.setUser(user.get());
            Address savedAddress = iAddressRepo.save(newAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 3. Update an existing address for a specific user
    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer addressId, @RequestBody Address updatedAddress) {
        Optional<Address> existingAddress = iAddressRepo.findById(addressId);
        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setAddress(updatedAddress.getAddress());
            Address savedAddress = iAddressRepo.save(address);
            return ResponseEntity.ok(savedAddress);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 4. Delete an address by ID
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressId) {
        Optional<Address> existingAddress = iAddressRepo.findById(addressId);
        if (existingAddress.isPresent()) {
            iAddressRepo.deleteById(addressId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

