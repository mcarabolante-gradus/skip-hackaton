package org.mcarabolante;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppDb {
    private final Jdbi orderJdbi;

    public static AppDb build(final SkipConfiguration configuration,
                              final Environment environment){
        return new AppDb(jdbi(environment, configuration.getOrderDataSourceFactory(), "order_db"));
    }

    private static Jdbi jdbi(Environment environment, PooledDataSourceFactory dataSourceFactory, String name){
        return new JdbiFactory().build(environment, dataSourceFactory, name);
    }

    public <T> T instance(Class<T> clazz){
        return orderJdbi.onDemand(clazz);
    }
}
