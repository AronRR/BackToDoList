package org.acme.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Todo {
    private UUID id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private UUID userId;
    private UUID listaId;
    private LocalDate dueDate;
    private String priority;

    public Todo(){

    }
    public Todo(UUID id, String title, String description, boolean completed, LocalDateTime createdAt, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Todo(UUID id, String title, String description, boolean completed, LocalDateTime createdAt, UUID userId, UUID listaId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.userId = userId;
        this.listaId = listaId;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getListaId() {
        return listaId;
    }

    public void setListaId(UUID listaId) {
        this.listaId = listaId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
