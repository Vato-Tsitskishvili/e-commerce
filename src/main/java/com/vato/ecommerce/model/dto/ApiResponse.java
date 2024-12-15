package com.vato.ecommerce.model.dto;

public record ApiResponse(
        String message,
        boolean status
) {
}
