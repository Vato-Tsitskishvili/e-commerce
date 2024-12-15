package com.vato.ecommerce.model.dto;

public record PaymentLinkResponse(
        String paymentLinkUrl,
        String paymentLinkId
) {
}
