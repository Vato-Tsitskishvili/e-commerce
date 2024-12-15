package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.model.dto.ApiResponse;
import com.vato.ecommerce.model.dto.PaymentLinkResponse;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.service.OrderService;
import com.vato.ecommerce.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    public PaymentController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPayment(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.findOrderById(orderId);
        PaymentLinkResponse response = paymentService.createPaymentLink(order);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> redirect(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("orderId") Long orderId
    ) throws OrderNotFoundException {
        Order order = orderService.findOrderById(orderId);
        String message = paymentService.redirect(order, paymentId);
        ApiResponse response = new ApiResponse(message, true);

        return ResponseEntity.ok(response);
    }
}
