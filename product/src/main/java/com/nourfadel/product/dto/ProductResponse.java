package com.nourfadel.product.dto;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponse {

    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
}
