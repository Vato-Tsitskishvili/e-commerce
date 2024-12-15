package com.vato.ecommerce.model.dto;

public record AuthResponse(
        String jwt,
        String message
) {
}
