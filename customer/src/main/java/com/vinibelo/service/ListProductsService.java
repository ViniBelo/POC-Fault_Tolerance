package com.vinibelo.service;

import com.vinibelo.service.connection.ClientService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.smallrye.faulttolerance.api.RateLimitException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

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
    RetryConfig retryConfig = RetryConfig.custom()
            .maxAttempts(2)
            .waitDuration(Duration.ofMillis(100))
            .retryExceptions(Exception.class)
            .build();
    RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
    RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("Custom RateLimiter");
    RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);
    Retry retry = retryRegistry.retry("Custom Retry");
    private static final Logger LOG = Logger.getLogger(ListProductsService.class);


    public List<String> getProducts() {
        Supplier<List<String>> productsSupplier = Retry.decorateSupplier(
                retry,
                RateLimiter.decorateSupplier(
                        rateLimiter, () -> {
                            LOG.info("Calling store API");
                            return callStoreApi();
                        }
                )
        );
        try {
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
