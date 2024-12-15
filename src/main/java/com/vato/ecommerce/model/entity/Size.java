package com.vato.ecommerce.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Size {

    private String name;
    private int quantity;
}
