package com.vinibelo.model;

import java.time.Instant;
import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private List<String> products;
    private Instant createdAt;
    private Instant updatedAt;

    public Order(String id, String customerId, List<String> products, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
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
