package org.mcarabolante.authentication;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.mcarabolante.domain.customer.Customer;
import org.mcarabolante.domain.customer.CustomerDAO;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CustomerAuth extends AbstractContainerRequestValueFactory<Customer> {
    private final CustomerDAO customerDAO;

    @Inject
    public CustomerAuth(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer provide() {
        return customerDAO.findForToken(readAuthenticationToken())
                .orElseThrow(() -> new WebApplicationException(Response.Status.UNAUTHORIZED));
    }


    private String readAuthenticationToken() {
        String authorization = getContainerRequest().getHeaderString("Authorization");
        if(StringUtils.isNotEmpty(authorization)){
            return TokenGenerator.hashCode(authorization).toString();
        }
        return "";
    }
}
