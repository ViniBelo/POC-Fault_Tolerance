package com.vinibelo.service;

import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ListProductsService {
    @Inject
    @RestClient
    ClientService clientService;

    @Retry(maxRetries = 1)
    @RateLimit(value = 15, window = 10)
    public List<String> getProducts() {
        return clientService.getOrder().getProducts();
    }
}
