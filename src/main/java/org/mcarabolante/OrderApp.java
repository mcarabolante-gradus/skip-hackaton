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
                bindFactory(CustomerAuth.class).to(Customer.class).in(RequestScoped.class);
            }
        });


        // TODO - DI
        environment.jersey().register(new StoreResource(appDb.instance(StoreDAO.class), appDb.instance(ProductDAO.class)));
        environment.jersey().register(new ProductResource(appDb.instance(ProductDAO.class)));
        environment.jersey().register(new CuisineResource(appDb.instance(CuisineDAO.class), appDb.instance(StoreDAO.class)));
        environment.jersey().register(new CustomerResource(new CustomerValidator(appDb.instance(CustomerDAO.class)), new CustomerService(appDb.instance(CustomerDAO.class), appDb.instance(SessionDAO.class))));
        environment.jersey().register(new OrderResource(appDb.instance(OrderDAO.class), new OrderValidator(appDb.instance(StoreDAO.class))));
    }

}
