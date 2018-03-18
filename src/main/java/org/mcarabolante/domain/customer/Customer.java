package org.mcarabolante.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Customer {
    private final Long id;
    private final String email;
    private final String name;
    private final String address;
    private final Instant createdAt;
}
