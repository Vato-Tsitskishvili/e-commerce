package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.CartNotFoundException;
import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.AddItemRequest;
import com.vato.ecommerce.model.dto.ApiResponse;
import com.vato.ecommerce.model.entity.Cart;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.CartService;
import com.vato.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserNotFoundException, CartNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestBody AddItemRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, ProductNotFoundException, CartNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        String message = cartService.addCartItem(user.getId(), request);

        ApiResponse response = new ApiResponse(message, true);
        return ResponseEntity.ok(response);
    }
}
