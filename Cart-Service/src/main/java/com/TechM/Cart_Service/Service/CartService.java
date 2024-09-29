package com.TechM.Cart_Service.Service;

import com.TechM.Cart_Service.Entity.Cart;
import com.TechM.Cart_Service.Entity.CartItem;
import com.TechM.Cart_Service.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Get the cart for a specific user
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Add an item to the cart
    public Cart addItemToCart(Long userId, CartItem item) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }

        item.setProductId(item.getProductId()); // Ensure productId is set
        cart.getCartItems().add(item);
        return cartRepository.save(cart);
    }

    // Update item quantity in the cart
    public Cart updateCartItem(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return null;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                return cartRepository.save(cart); // Save after updating quantity
            }
        }
        return null; // Return null if product not found
    }

    // Remove an item from the cart
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return null;

        boolean removed = cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        if (removed) {
            return cartRepository.save(cart); // Save if item was removed
        }
        return null; // Return null if item not found
    }

    // Clear the cart
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getCartItems().clear();
            cartRepository.save(cart);
        }
    }
}
