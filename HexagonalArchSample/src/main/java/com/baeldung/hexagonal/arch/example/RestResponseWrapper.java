package com.baeldung.hexagonal.arch.example;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

public class RestResponseWrapper {

    public Response sendOkResponse() {
        return Response.ok().build();
    }
    
    public Response sendResponse(Object entity) {
        return Response.status(Status.OK).entity(entity).build();
    }
}
