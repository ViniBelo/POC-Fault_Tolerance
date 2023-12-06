package com.vinibelo.service;

import com.vinibelo.exception.NotFoundExceptionHandler;
import com.vinibelo.exception.RateLimitExceptionHandler;
import com.vinibelo.service.connection.ClientService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.quarkus.logging.Log;
import io.smallrye.faulttolerance.api.RateLimitException;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@ApplicationScoped
public class ListProductsService {
    @Inject
    @RestClient
    ClientService clientService;
    RateLimiterConfig config = RateLimiterConfig.custom()
            .limitRefreshPeriod(Duration.ofSeconds(10))
            .limitForPeriod(15)
            .timeoutDuration(Duration.ofMillis(100))
            .build();
    RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
    RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("Custom");
    private static final Logger LOG = Logger.getLogger(ListProductsService.class);


    public List<String> getProducts() {
        try {
            Supplier<List<String>> productsSupplier = RateLimiter.decorateSupplier(
                    rateLimiter, () -> {
                        LOG.info("Calling store API");
                        return callStoreApi();
                    }
            );
            return productsSupplier.get();
        } catch (RequestNotPermitted e) {
            LOG.error("Rate limit exceeded: " + e.getMessage() + " " + e.getClass());
            throw new RateLimitException();
        } catch (Exception e) {
            LOG.error("Bad Request: " + e.getMessage() + " " + e.getClass());
            throw new BadRequestException();
        }
    }

    public List<String> callStoreApi() {
        return clientService.getOrder().getProducts();
    }
}
