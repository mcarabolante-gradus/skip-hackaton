package org.mcarabolante.domain.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class Order {
    private final Long id;
    private final Long customerId;
    @NotNull
    private final String deliveryAddress;
    @NotNull
    private final String contact;
    @NotNull
    private final Long storeId;
    private final OrderStatus status;
    private final Instant lastUpdate;
    @NotNull
    private final List<OrderItem> orderItems;

    @JsonProperty("total")
    public BigDecimal total(){
        return orderItems.stream()
                .map(OrderItem::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
