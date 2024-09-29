package com.TechM.Cart_Service.ServiceTest;

import com.TechM.Cart_Service.Entity.Wishlist;
import com.TechM.Cart_Service.Repository.WishlistRepository;
import com.TechM.Cart_Service.Service.WishlistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetWishlistByUserId() {
        Long userId = 1L;
        List<Wishlist> wishlistItems = new ArrayList<>();
        when(wishlistRepository.findByUserId(userId)).thenReturn(wishlistItems);

        List<Wishlist> result = wishlistService.getWishlistByUserId(userId);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testAddItemToWishlist() {
        Long userId = 1L;
        Long productId = 100L;

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(productId);
        wishlist.setAddedDate(LocalDateTime.now());

        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist result = wishlistService.addItemToWishlist(userId, productId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(productId, result.getProductId());
    }

    @Test
    void testRemoveItemFromWishlist() {
        Long userId = 1L;
        Long productId = 100L;

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(productId);

        when(wishlistRepository.findByUserIdAndProductId(userId, productId)).thenReturn(wishlist);
        doNothing().when(wishlistRepository).delete(wishlist);

        wishlistService.removeItemFromWishlist(userId, productId);
        verify(wishlistRepository, times(1)).delete(wishlist);
    }
}
