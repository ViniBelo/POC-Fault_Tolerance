package com.vinibelo.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ListProductsService {
    @Inject
    @RestClient
    ClientService clientService;

    @Consumes(MediaType.APPLICATION_JSON)
    public List<String> getProducts() {
        return clientService.getOrder().getProducts();
    }
}
