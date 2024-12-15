package com.vato.ecommerce.model.dto;

public record AddItemRequest(

        Long productId,
        String size,
        int quantity,
        Integer price
) {
}
