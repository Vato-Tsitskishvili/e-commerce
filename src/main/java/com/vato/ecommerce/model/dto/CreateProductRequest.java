package com.vato.ecommerce.model.dto;

import com.vato.ecommerce.model.entity.Size;

import java.util.HashSet;
import java.util.Set;

public record CreateProductRequest(
        String title,
        String description,
        int price,
        int discountedPrice,
        int discountPercent,
        int quantity,
        String brand,
        String color,
        Set<Size> sizes,
        String imageUrl,
        String topLevelCategory,
        String secondLevelCategory,
        String thirdLevelCategory
) {
}
