package org.mcarabolante.resources;

import org.apache.commons.lang3.StringUtils;
import org.mcarabolante.commons.ResponseUtil;
import org.mcarabolante.domain.customer.Customer;
import org.mcarabolante.domain.order.Order;
import org.mcarabolante.domain.order.OrderDAO;
import org.mcarabolante.domain.order.OrderValidator;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.mcarabolante.commons.ResponseUtil.fromOptional;

@Path("/api/v1/Order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderDAO orderDAO;
    private final OrderValidator orderValidator;

    public OrderResource(OrderDAO orderDAO, OrderValidator orderValidator) {
        this.orderDAO = orderDAO;
        this.orderValidator = orderValidator;
    }

    @GET
    @Path("{orderId}")
    public Response find(@Context Customer customer, @PathParam("orderId") Long orderId){
        return fromOptional(
                orderDAO.findById(orderId)
                        .filter(order -> order.getCustomerId().equals(customer.getId())),
                "Order " + orderId + " not found");
    }

    @POST
    public Response save(@Context Customer customer, @Valid Order order){
        String error = orderValidator.isValid(order);
        if(isNotEmpty(error)){
            return ResponseUtil.badRequest(error);
        }

        return Response.ok(orderDAO.insert(order, customer)).build();
    }

    @GET
    @PathParam("customer")
    public Customer customer(@Context Customer customer){
        return null;
    }
}
