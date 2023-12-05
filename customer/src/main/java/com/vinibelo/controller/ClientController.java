package com.vinibelo.controller;

import com.vinibelo.ClientService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/service-a")
public class ClientController {
    @Inject
    @RestClient
    ClientService clientService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getOrder() {
        return clientService.getOrder();
    }
}
