package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.CartItemNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.ApiResponse;
import com.vato.ecommerce.model.entity.CartItem;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.CartItemService;
import com.vato.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final UserService userService;
    private final CartItemService cartItemService;

    public CartItemController(UserService userService, CartItemService cartItemService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, CartItemNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        String message = cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse(message, true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @RequestBody CartItem cartItem,
            @PathVariable("cartItemId") Long cartItemId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, CartItemNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        return ResponseEntity.ok(updatedCartItem);
    }
}
