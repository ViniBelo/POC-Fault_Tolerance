package com.vinibelo.model;

import java.time.Instant;

public class Order {
    private String id;
    private String customer;
    private Instant createdAt;
    private Instant updatedAt;

    public Order(String id, String customer, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
