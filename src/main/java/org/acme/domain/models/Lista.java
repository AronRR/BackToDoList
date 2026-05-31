package org.acme.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Lista {
    private UUID id;
    private String name;
    private String color;
    private UUID userId;
    private LocalDateTime createdAt;

    public Lista(){

    }
    public Lista(UUID id, String name, String color, UUID userId, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.userId = userId;
        this.createdAt = createdAt;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
