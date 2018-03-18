package org.mcarabolante.commons;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class ResponseUtil {

    public static <T> Response fromOptional(Optional<T> optional, String notFoundText){
        return optional
                .map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(notFoundText)))
                .build();
    }

    public static Response badRequest(String invalidEmail) {
        return Response.status(BAD_REQUEST).entity(new ErrorResponse(invalidEmail)).build();
    }
}
