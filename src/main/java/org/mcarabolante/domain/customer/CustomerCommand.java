package org.mcarabolante.domain.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.mcarabolante.commons.BcryptUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCommand {
    private final Long id;
    @NotNull
    private final String email;
    @NotNull
    private final String name;
    @NotNull
    private final String address;
    @NotNull
    private final String password;

    @JsonCreator
    public CustomerCommand(@JsonProperty("id") Long id,
                           @JsonProperty("email") String email,
                           @JsonProperty("name") String name,
                           @JsonProperty("address") String address,
                           @JsonProperty("password") String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = BcryptUtil.hash(password);
    }

    public boolean isInsert(){
        return id == null || id <= 0;
    }
}
