package com.TechM.Cart_Service.ServiceTest;

import com.TechM.Cart_Service.Entity.Cart;
import com.TechM.Cart_Service.Entity.CartItem;
import com.TechM.Cart_Service.Repository.CartRepository;
import com.TechM.Cart_Service.Service.CartService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCartByUserId() {
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setUserId(userId);

        when(cartRepository.findByUserId(userId)).thenReturn(cart);

        Cart result = cartService.getCartByUserId(userId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }

    @Test
    void testAddItemToCart() {
        Long userId = 1L;
        CartItem item = new CartItem();
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCartItems(new ArrayList<>());

        when(cartRepository.findByUserId(userId)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addItemToCart(userId, item);
        assertNotNull(result);
        assertEquals(1, result.getCartItems().size());
    }

    @Test
    void testUpdateCartItem() {
        Long userId = 1L;
        Long productId = 100L;
        int newQuantity = 3;

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(1);
        
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCartItems(List.of(item));

        when(cartRepository.findByUserId(userId)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.updateCartItem(userId, productId, newQuantity);
        assertNotNull(result);
        assertEquals(newQuantity, result.getCartItems().get(0).getQuantity());
    }

    @Test
    void testRemoveItemFromCart() {
        Long userId = 1L;
        Long productId = 100L;

        CartItem item = new CartItem();
        item.setProductId(productId);

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setCartItems(new ArrayList<>(List.of(item)));

        when(cartRepository.findByUserId(userId)).thenReturn(cart);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.removeItemFromCart(userId, productId);
        assertNotNull(result);
        assertTrue(result.getCartItems().isEmpty());
    }
}
