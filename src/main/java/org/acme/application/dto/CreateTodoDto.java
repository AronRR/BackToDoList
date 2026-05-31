package org.acme.application.dto;

import java.util.UUID;

public class CreateTodoDto {
    private String title;
    private String description;
    private UUID listaId;
    public CreateTodoDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public CreateTodoDto(){

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

    public UUID getListaId() {
        return listaId;
    }

    public void setListaId(UUID listaId) {
        this.listaId = listaId;
    }
}
