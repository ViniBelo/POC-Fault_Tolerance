package com.vinibelo.controller;

import com.vinibelo.model.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Instant;

@Path("/service-b")
public class OrderController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("details")
    public Order hello() {
        return new Order("ohi432ioda0", "Henrique", Instant.now(), Instant.now());
    }
}
