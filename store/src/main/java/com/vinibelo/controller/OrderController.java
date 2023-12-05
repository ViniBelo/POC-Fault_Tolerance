package com.vinibelo.controller;

import com.vinibelo.model.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/service-b")
public class OrderController {
    static int rateLimiter = 0;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("details")
    public Response hello() {
        List<String> products = Arrays.asList("TV 42", "Smartphone Samsung");
        var order = new Order("ohi432ioda0", "uh534i78uyh", products, Instant.now(), Instant.now());
        rateLimiter++;
        System.out.println(rateLimiter);
        if (rateLimiter % 10 == 0) {
            return Response.status(429).build();
        }
        return Response.ok(order).build();
    }
}
