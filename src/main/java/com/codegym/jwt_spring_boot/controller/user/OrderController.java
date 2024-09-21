package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.Cart;
import com.codegym.jwt_spring_boot.model.CartLineItem;
import com.codegym.jwt_spring_boot.model.Order;
import com.codegym.jwt_spring_boot.model.User;
import com.codegym.jwt_spring_boot.repository.IAccountRepo;
import com.codegym.jwt_spring_boot.repository.ICartLineItemRepo;
import com.codegym.jwt_spring_boot.repository.ICartRepo;
import com.codegym.jwt_spring_boot.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ICartRepo iCartRepo;

    @Autowired
    private ICartLineItemRepo iCartLineItemRepo;

    @Autowired
    private IOrderRepo iOrderRepo;

    @Autowired
    private IAccountRepo iUserRepo;

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkoutOrder(@RequestParam String shippingAddress,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deliveryTime,
                                               Authentication authentication) {
        // Lấy thông tin user đang login
        String username = authentication.getName();
        User user = iUserRepo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Lấy giỏ hàng của user
        Optional<Cart> cart = iCartRepo.findByUserId(user.getId());
        if (!cart.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Tạo mới Order từ giỏ hàng hiện tại
        Order order = new Order();
        order.setAddress(shippingAddress);
        order.setDeliveryTime(Timestamp.valueOf(deliveryTime).toString());
        order.setTotalPrice(cart.get().getTotalPrice().floatValue());  // Convert Double to Float

        // Lưu Order vào cơ sở dữ liệu
        Order savedOrder = iOrderRepo.save(order);

        // Xóa mềm các CartLineItem trong giỏ hàng
        List<CartLineItem> cartLineItems = iCartLineItemRepo.findByCartId(cart.get().getId());
        for (CartLineItem cartLineItem : cartLineItems) {
            cartLineItem.setIsDeleted(true);
            cartLineItem.setOrder(savedOrder); // Gán các CartLineItem này vào Order vừa tạo
            iCartLineItemRepo.save(cartLineItem);
        }

        return ResponseEntity.ok(savedOrder);
    }
}

