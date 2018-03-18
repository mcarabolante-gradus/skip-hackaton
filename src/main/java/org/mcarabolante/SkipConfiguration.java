package org.mcarabolante;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
public class SkipConfiguration extends Configuration {
    @Valid
    @NotNull @JsonProperty
    private DataSourceFactory orderDataSourceFactory = new DataSourceFactory();

}
