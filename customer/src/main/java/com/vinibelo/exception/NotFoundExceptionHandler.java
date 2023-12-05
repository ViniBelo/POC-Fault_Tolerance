package com.vinibelo.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler extends Throwable implements ExceptionMapper<NotFoundException> {
    public Response toResponse(NotFoundException exception) {
        return Response.status(404).build();
    }
}
