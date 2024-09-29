package com.TechM.Cart_Service.Controller;

import com.TechM.Cart_Service.Entity.Cart;
import com.TechM.Cart_Service.Entity.CartItem;
import com.TechM.Cart_Service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get the cart for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Add item to the cart
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        Cart cart = cartService.addItemToCart(userId, item);
        return ResponseEntity.ok(cart);
    }

    // Update item quantity in the cart
    @PutMapping("/{userId}/update/{productId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        Cart cart = cartService.updateCartItem(userId, productId, quantity);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Remove an item from the cart
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        Cart cart = cartService.removeItemFromCart(userId, productId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Clear the cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
