package com.vinibelo.service;

import com.vinibelo.model.Order;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("service-b")
@RegisterRestClient(baseUri = "http://localhost:8081", configKey = "com.vinibelo.service-api")
@RegisterClientHeaders
public interface ClientService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("details")
    Order getOrder();
}
