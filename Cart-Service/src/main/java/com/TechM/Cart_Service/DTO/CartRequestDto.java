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
public class CartRequestDto {
    private String userId;  // Associate a user with the cart
    private List<CartItemRequestDto> cartItems;  // Cart items
    
    
    
	
    
    
}
