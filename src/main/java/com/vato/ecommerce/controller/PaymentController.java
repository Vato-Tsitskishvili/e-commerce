package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.model.dto.Response;
import com.vato.ecommerce.model.dto.PaymentLinkResponse;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.service.OrderService;
import com.vato.ecommerce.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create Payment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentLinkResponse.class)
                    )
            )
    })
    @PostMapping("/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPayment(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws OrderNotFoundException {
        Order order = orderService.findOrderById(orderId);
        PaymentLinkResponse response = paymentService.createPaymentLink(order);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Redirect to Payment Success Page")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Response> redirect(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("orderId") Long orderId
    ) throws OrderNotFoundException {
        Order order = orderService.findOrderById(orderId);
        String message = paymentService.redirect(order, paymentId);
        Response response = new Response(message, true);

        return ResponseEntity.ok(response);
    }
}
