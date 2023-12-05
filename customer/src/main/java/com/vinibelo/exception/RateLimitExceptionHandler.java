package com.vinibelo.exception;

import io.smallrye.faulttolerance.api.RateLimitException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RateLimitExceptionHandler extends Throwable implements ExceptionMapper<RateLimitException> {
    public Response toResponse(RateLimitException exception) {
        return Response.status(429).build();
    }
}
