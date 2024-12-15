package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.model.dto.ApiResponse;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.service.OrderService;
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

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.confirmedOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.shippedOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliveredOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.deliveredOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelledOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.canceledOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        String message = orderService.deleteOrder(orderId);
        ApiResponse response = new ApiResponse(message, true);

        return ResponseEntity.ok(response);
    }
}
