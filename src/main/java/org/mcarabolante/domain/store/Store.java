package org.mcarabolante.domain.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Store {
    private final Long id;
    private final String name;
    private final String address;
    private final Long cousineId;
}
