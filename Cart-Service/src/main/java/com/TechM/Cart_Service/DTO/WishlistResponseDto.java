package com.TechM.Cart_Service.DTO;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistResponseDto {
    private Long wishlistId;  // Wishlist ID
    private String userId;  // User ID
    private Long productId;  // Product ID
    private LocalDateTime addedDate;  // Date when the product was added
    
    
    
	
    
    
}

