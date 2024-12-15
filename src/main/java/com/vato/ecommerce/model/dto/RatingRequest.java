package com.vato.ecommerce.model.dto;

public record RatingRequest(
        Long productId,
        double rating
) {
}
