package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.CartItemNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.CartItem;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.CartItemService;
import com.vato.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Delete Cart Item")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            )
    })
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Response> deleteCartItem(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, CartItemNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        String message = cartItemService.removeCartItem(user.getId(), cartItemId);

        Response response = new Response(message, true);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Cart Item")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            )
    })
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
