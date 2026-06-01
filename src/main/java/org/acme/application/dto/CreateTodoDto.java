package org.acme.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public class CreateTodoDto {
    private String title;
    private String description;
    private UUID listaId;
    private String priority;
    private LocalDate dueDate;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
