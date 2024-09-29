package com.TechM.Cart_Service.Repository;

import com.TechM.Cart_Service.Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserId(Long userId);
    Wishlist findByUserIdAndProductId(Long userId, Long productId);
}
