package org.mcarabolante;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.mcarabolante.authentication.CustomerAuth;
import org.mcarabolante.domain.cuisine.CuisineDAO;
import org.mcarabolante.domain.customer.Customer;
import org.mcarabolante.domain.customer.CustomerDAO;
import org.mcarabolante.domain.customer.CustomerService;
import org.mcarabolante.domain.customer.CustomerValidator;
import org.mcarabolante.domain.order.OrderDAO;
import org.mcarabolante.domain.order.OrderValidator;
import org.mcarabolante.domain.product.ProductDAO;
import org.mcarabolante.domain.session.SessionDAO;
import org.mcarabolante.domain.store.StoreDAO;
import org.mcarabolante.resources.*;

public class OrderApp extends Application<SkipConfiguration> {

    public static void main(final String[] args) throws Exception {
        new OrderApp().run(args);
    }

    @Override
    public void initialize(final Bootstrap<SkipConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<SkipConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(SkipConfiguration configuration) {
                return configuration.getOrderDataSourceFactory();
            }
        });

    }

    @Override
    public void run(final SkipConfiguration configuration,
                    final Environment environment) {
        AppDb appDb = AppDb.build(configuration, environment);

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(appDb.instance(CustomerDAO.class)).to(CustomerDAO.class);
                bind(appDb.instance(ProductDAO.class)).to(ProductDAO.class);
                bind(appDb.instance(StoreDAO.class)).to(StoreDAO.class);
                bind(appDb.instance(CuisineDAO.class)).to(CuisineDAO.class);
                bind(appDb.instance(SessionDAO.class)).to(SessionDAO.class);
                bind(appDb.instance(OrderDAO.class)).to(OrderDAO.class);
                bind(CustomerService.class).to(CustomerService.class);
                bind(CustomerValidator.class).to(CustomerValidator.class);
                bind(OrderValidator.class).to(OrderValidator.class);

                bindFactory(CustomerAuth.class).to(Customer.class).in(RequestScoped.class);
            }
        });

        environment.jersey().register(StoreResource.class);
        environment.jersey().register(ProductResource.class);
        environment.jersey().register(CuisineResource.class);
        environment.jersey().register(CustomerResource.class);
        environment.jersey().register(OrderResource.class);
    }

}
