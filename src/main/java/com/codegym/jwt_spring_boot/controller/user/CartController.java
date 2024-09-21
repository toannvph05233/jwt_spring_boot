package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.*;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import com.codegym.jwt_spring_boot.repository.ICartLineItemRepo;
import com.codegym.jwt_spring_boot.repository.ICartRepo;
import com.codegym.jwt_spring_boot.repository.IVariantProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartRepo iCartRepo;

    @Autowired
    private ICartLineItemRepo iCartLineItemRepo;

    @Autowired
    private IVariantProductRepo iVariantProductRepo;

    @Autowired
    private IAccountRepo iUserRepo;

    // Add product to the cart and calculate total price
    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Integer variantProductId,
                                                 @RequestParam Integer quantity,
                                                 Authentication authentication) {
        String username = authentication.getName();
        User user = iUserRepo.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Get user's cart, or create a new cart if it doesn't exist
        Cart cart = iCartRepo.findByUserId(user.getId())
                .orElseGet(() -> createNewCart(user));

        // Check if variant product exists
        VariantProduct variantProduct = iVariantProductRepo.findById(variantProductId).get();

        // Add the product as a CartLineItem
        CartLineItem cartLineItem = iCartLineItemRepo.findByCartIdAndVariantProductId(cart.getId(), variantProductId)
                .orElse(new CartLineItem());

        if (cartLineItem.getId() == null) { // Nếu là item mới
            cartLineItem.setQuantity(0); // Thiết lập giá trị ban đầu
        }

        cartLineItem.setCart(cart);
        cartLineItem.setVariantProduct(variantProduct);
        cartLineItem.setQuantity(cartLineItem.getQuantity() + quantity);
        cartLineItem.setTotalPrice(cartLineItem.getQuantity() * variantProduct.getPrice());
        cartLineItem.setAddedDate(new Timestamp(System.currentTimeMillis()));

        iCartLineItemRepo.save(cartLineItem);

        // Recalculate the total price for the cart
        double totalPrice = calculateTotalPrice(cart.getId());
        cart.setTotalPrice(totalPrice);
        iCartRepo.save(cart);

        return ResponseEntity.ok(cart);
    }

    // Helper method to create a new cart for the user
    private Cart createNewCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedDate(LocalDate.now());
        cart.setTotalPrice(0.0);
        return iCartRepo.save(cart);
    }

    // Helper method to calculate total price for a cart
    private double calculateTotalPrice(Integer cartId) {
        List<CartLineItem> cartLineItems = iCartLineItemRepo.findByCartId(cartId);
        return cartLineItems.stream().mapToDouble(CartLineItem::getTotalPrice).sum();
    }
}

