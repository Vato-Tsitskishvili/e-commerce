package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.CartNotFoundException;
import com.vato.ecommerce.exceptions.ProductNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.AddItemRequest;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Cart;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.CartService;
import com.vato.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find User's Cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cart.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserNotFoundException, CartNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "Add Item to Cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            )
    })
    @PutMapping("/add")
    public ResponseEntity<Response> addItemToCart(
            @RequestBody AddItemRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, ProductNotFoundException, CartNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        String message = cartService.addCartItem(user.getId(), request);

        Response response = new Response(message, true);
        return ResponseEntity.ok(response);
    }
}
