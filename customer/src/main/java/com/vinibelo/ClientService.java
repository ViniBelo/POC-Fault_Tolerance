package com.vinibelo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("service-b")
@RegisterRestClient(baseUri = "http://localhost:8081", configKey = "service-api")
@RegisterClientHeaders
@ApplicationScoped
public interface ClientService {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("details")
    String getOrder();
}
