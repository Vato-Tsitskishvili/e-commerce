package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Get All Orders")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found All Orders",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Order.class)
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Update Order Status to Confirmed")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status has been updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update order status"
            )
    })
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrder(
            @PathVariable("orderId") Long orderId,
            @Parameter(description = "Jwt for authorized access")
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.confirmedOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Update Order Status to Confirmed")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status has been updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update order status"
            )
    })
    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrder(
            @PathVariable("orderId") Long orderId,
            @Parameter(description = "Jwt for authorized access")
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.shippedOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Update Order Status to Delivered")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status has been updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update order status"
            )
    })
    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliveredOrder(
            @PathVariable("orderId") Long orderId,
            @Parameter(description = "Jwt for authorized access")
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.deliveredOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Update Order Status to Cancelled")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order status has been updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update order status"
            )
    })
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelledOrder(
            @PathVariable("orderId") Long orderId,
            @Parameter(description = "Jwt for authorized access")
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.canceledOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Delete Order By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order has been deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to update order status"
            )
    })
    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<Response> deleteOrder(
            @PathVariable("orderId") Long orderId,
            @Parameter(description = "Jwt for authorized access")
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        String message = orderService.deleteOrder(orderId);
        Response response = new Response(message, true);

        return ResponseEntity.ok(response);
    }
}
