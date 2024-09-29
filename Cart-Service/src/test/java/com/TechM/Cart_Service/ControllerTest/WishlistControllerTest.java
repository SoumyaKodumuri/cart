package com.TechM.Cart_Service.ControllerTest;

import com.TechM.Cart_Service.Controller.WishlistController;
import com.TechM.Cart_Service.Entity.Wishlist;
import com.TechM.Cart_Service.Service.WishlistService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
//import org.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WishlistService wishlistService;

    @Test
    public void testGetWishlist() throws Exception {
        Long userId = 1L;
        List<Wishlist> wishlist = List.of(new Wishlist());

        when(wishlistService.getWishlistByUserId(userId)).thenReturn(wishlist);

        mockMvc.perform(get("/wishlist/{userId}", userId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddItemToWishlist() throws Exception {
        Long userId = 1L;
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(100L);

        when(wishlistService.addItemToWishlist(eq(userId), eq(100L))).thenReturn(wishlist);

        mockMvc.perform(post("/wishlist/{userId}/add", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 100}"))
                .andExpect(status().isOk());
    }
}
