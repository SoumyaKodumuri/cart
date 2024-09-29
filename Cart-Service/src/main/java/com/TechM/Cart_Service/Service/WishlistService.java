package com.TechM.Cart_Service.Service;

import com.TechM.Cart_Service.Entity.Wishlist;
import com.TechM.Cart_Service.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Get wishlist by user ID
    public List<Wishlist> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    // Add item to wishlist
    public Wishlist addItemToWishlist(Long userId, Long productId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(productId);
        wishlist.setAddedDate(LocalDateTime.now());
        return wishlistRepository.save(wishlist);
    }

    // Update wishlist item
    public Wishlist updateWishlistItem(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(userId, productId);
        if (wishlist != null) {
            wishlist.setAddedDate(LocalDateTime.now());  // Just updating date, modify logic as needed
            return wishlistRepository.save(wishlist);
        }
        return null;
    }

    // Delete an item from wishlist
    public void removeItemFromWishlist(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(userId, productId);
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
        }
    }

    // Clear entire wishlist
    public void clearWishlist(Long userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserId(userId);
        wishlistRepository.deleteAll(wishlistItems);
    }
}
