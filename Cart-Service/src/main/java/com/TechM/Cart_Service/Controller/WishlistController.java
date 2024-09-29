package com.TechM.Cart_Service.Controller;

import com.TechM.Cart_Service.Entity.Wishlist;
import com.TechM.Cart_Service.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get wishlist by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlist(@PathVariable Long userId) {
        List<Wishlist> wishlist = wishlistService.getWishlistByUserId(userId);
        if (wishlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }

    // Add item to wishlist
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<Wishlist> addItemToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        Wishlist wishlist = wishlistService.addItemToWishlist(userId, productId);
        return ResponseEntity.ok(wishlist);
    }

    // Update wishlist item (e.g., updating the date)
    @PutMapping("/{userId}/update/{productId}")
    public ResponseEntity<Wishlist> updateWishlistItem(@PathVariable Long userId, @PathVariable Long productId) {
        Wishlist updatedWishlist = wishlistService.updateWishlistItem(userId, productId);
        if (updatedWishlist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedWishlist);
    }

    // Remove item from wishlist
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeItemFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeItemFromWishlist(userId, productId);
        return ResponseEntity.ok().build();
    }

    // Clear the entire wishlist
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearWishlist(@PathVariable Long userId) {
        wishlistService.clearWishlist(userId);
        return ResponseEntity.ok().build();
    }
}
