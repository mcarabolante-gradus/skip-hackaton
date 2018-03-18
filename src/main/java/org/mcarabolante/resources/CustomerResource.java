package org.mcarabolante.resources;

import org.mcarabolante.commons.ResponseUtil;
import org.mcarabolante.domain.customer.CustomerCommand;
import org.mcarabolante.domain.customer.CustomerService;
import org.mcarabolante.domain.customer.CustomerValidator;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.mcarabolante.commons.ResponseUtil.fromOptional;

@Path("api/v1/Customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final CustomerValidator customerValidator;
    private final CustomerService customerService;

    @Inject
    public CustomerResource(CustomerValidator customerValidator, CustomerService customerService) {
        this.customerValidator = customerValidator;
        this.customerService = customerService;
    }


    @POST
    public Response saveCustomer(@Valid CustomerCommand customer){
        // TODO - needs a better abstraction (aka. custom validator)
        String invalidEmail = customerValidator.validateEmail(customer);
        if(!invalidEmail.isEmpty()){
            return ResponseUtil.badRequest(invalidEmail);
        }

        return Response.ok(customerService.saveAndGenerateToken(customer)).build();
    }

    @POST
    @Path("auth")
    public Response authenticate(@QueryParam("email") String email, @QueryParam("password") String password){
        return fromOptional(customerService.authenticate(email, password), "User and Password not found.");
    }
}
