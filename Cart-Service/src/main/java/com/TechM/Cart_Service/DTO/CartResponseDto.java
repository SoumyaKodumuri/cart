package com.TechM.Cart_Service.DTO;

import java.util.List;


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
public class CartResponseDto {
    private Long id;  // Cart ID
    private String userId;  // User associated with the cart
    private List<CartItemResponseDto> cartItems;  // Cart items
    private double totalPrice;  // Total price for all items
    
    
   
    
}

