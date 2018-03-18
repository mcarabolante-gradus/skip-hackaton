package org.mcarabolante.domain.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mcarabolante.domain.product.Product;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderItem {
    private final Long id;
    private final Long orderId;
    private final Long productId;
    private final Product product;
    private final BigDecimal price;
    private final BigDecimal quantity;

    @JsonProperty("total")
    public BigDecimal total(){
        return quantity.multiply(price);
    }

}
