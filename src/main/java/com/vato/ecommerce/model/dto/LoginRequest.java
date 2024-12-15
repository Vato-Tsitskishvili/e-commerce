package com.vato.ecommerce.model.dto;

public record LoginRequest(
        String email,
        String password
) {
}
