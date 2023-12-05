package com.vinibelo.model;

import java.time.Instant;
import java.util.List;

public class Order {
    private Long id;
    private Long customerId;
    private List<String> products;
    private Instant createdAt;
    private Instant updatedAt;

    public Order(Long id, Long customerId, List<String> products, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<String> getProducts() {
        return products;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
