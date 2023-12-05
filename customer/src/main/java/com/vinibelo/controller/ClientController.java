package com.vinibelo.controller;

import com.vinibelo.service.ListProductsService;
import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/service-a")
public class ClientController {
    @Inject
    ListProductsService listProductsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok(listProductsService.getProducts()).build();
    }
}
