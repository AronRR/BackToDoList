package org.acme.application.dto;

import org.acme.domain.models.Lista;
import org.acme.domain.models.Todo;

import java.util.List;

public class SearchResultDto {
    private List<Lista> listas;
    private List<Todo> todos;

    public SearchResultDto() {}

    public SearchResultDto(List<Lista> listas, List<Todo> todos) {
        this.listas = listas;
        this.todos = todos;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
