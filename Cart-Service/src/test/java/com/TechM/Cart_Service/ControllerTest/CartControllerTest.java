package com.TechM.Cart_Service.ControllerTest;

import com.TechM.Cart_Service.Controller.CartController;
import com.TechM.Cart_Service.Entity.Cart;
import com.TechM.Cart_Service.Entity.CartItem;
import com.TechM.Cart_Service.Service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    public void testGetCart() throws Exception {
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setUserId(userId);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);

        mockMvc.perform(get("/cart/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    public void testAddItemToCart() throws Exception {
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setUserId(userId);

        CartItem cartItem = new CartItem();
        cartItem.setProductId(100L);
        cartItem.setQuantity(1);

        when(cartService.addItemToCart(eq(userId), any(CartItem.class))).thenReturn(cart);

        mockMvc.perform(post("/cart/{userId}/add", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 100, \"productName\": \"Sample Product\", \"quantity\": 1, \"price\": 10.0}"))
                .andExpect(status().isOk());
    }
}
