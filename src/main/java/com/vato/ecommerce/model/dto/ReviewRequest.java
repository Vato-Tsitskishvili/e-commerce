package com.vato.ecommerce.model.dto;

public record ReviewRequest(
        Long productId,
        String review
) {
}
