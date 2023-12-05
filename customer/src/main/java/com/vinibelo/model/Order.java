package com.vinibelo.model;

import jakarta.json.bind.annotation.JsonbProperty;

import java.time.Instant;
import java.util.List;

public class Order {
    @JsonbProperty("id")
    private String id;
    @JsonbProperty("customerId")
    private String customerId;
    @JsonbProperty("products")
    private List<String> products;
    @JsonbProperty("createdAt")
    private Instant createdAt;
    @JsonbProperty("updatedAt")
    private Instant updatedAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
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
