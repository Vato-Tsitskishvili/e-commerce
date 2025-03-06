package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.CartNotFoundException;
import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Address;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.OrderService;
import com.vato.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Operation(summary = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Address shippingAddress,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, CartNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Operation(summary = "Users' Order History")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Order.class)
                            )
                    )
            )
    })
    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());

        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Find Order by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(
            @PathVariable("id") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, OrderNotFoundException {
        userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);

        return ResponseEntity.ok(order);
    }
}
