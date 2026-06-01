package org.acme.infrastructure.mapper;

import org.acme.domain.models.Todo;
import org.acme.infrastructure.entities.TodoEntity;

public class TodoMapper {

    private TodoMapper() {}

    public static Todo toDomain(TodoEntity entity) {
        Todo todo = new Todo();
        todo.setId(entity.getId());
        todo.setTitle(entity.getTitle());
        todo.setDescription(entity.getDescription());
        todo.setCompleted(entity.isCompleted());
        todo.setCreatedAt(entity.getCreatedAt());
        todo.setUserId(entity.getUserId());
        todo.setListaId(entity.getListaId());
        todo.setDueDate(entity.getDueDate());
        todo.setPriority(entity.getPriority());
        return todo;
    }

    public static TodoEntity toEntity(Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.getId());
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.isCompleted());
        entity.setCreatedAt(todo.getCreatedAt());
        entity.setUserId(todo.getUserId());
        entity.setListaId(todo.getListaId());
        entity.setDueDate(todo.getDueDate());
        entity.setPriority(todo.getPriority());
        return entity;
    }
}
