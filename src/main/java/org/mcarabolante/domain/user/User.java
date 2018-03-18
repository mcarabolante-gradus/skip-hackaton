package org.mcarabolante.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@AllArgsConstructor
@Getter
public class User {
    private final Long id;
    private final String username;
}
