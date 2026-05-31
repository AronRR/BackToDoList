package org.acme.application.dto;

import java.util.UUID;

public class UpdateTodoDto {
    private String title;
    private String description;
    private Boolean completed;
    private UUID listaId;

    public UpdateTodoDto() {}

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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public UUID getListaId() {
        return listaId;
    }

    public void setListaId(UUID listaId) {
        this.listaId = listaId;
    }
}
