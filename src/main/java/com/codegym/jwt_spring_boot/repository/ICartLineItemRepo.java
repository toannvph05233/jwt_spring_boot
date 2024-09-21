package com.codegym.jwt_spring_boot.repository;

import com.codegym.jwt_spring_boot.model.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICartLineItemRepo extends JpaRepository<CartLineItem, Integer> {
    Optional<CartLineItem> findByCartIdAndVariantProductId(Integer cartId, Integer variantProductId);
    List<CartLineItem> findByCartId(Integer cartId);
}

