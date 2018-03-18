package org.mcarabolante.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Product {
    private final Long id;
    private final Long storeId;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
