package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ForbiddenException;
import org.acme.application.dto.UpdateTodoDto;
import org.acme.domain.models.Todo;
import org.acme.domain.repository.TodoRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.UUID;

@ApplicationScoped
public class UpdateTodoUseCase {

    private final TodoRepository todoRepository;
    private final AuthContext authContext;

    @Inject
    public UpdateTodoUseCase(TodoRepository todoRepository, AuthContext authContext) {
        this.todoRepository = todoRepository;
        this.authContext = authContext;
    }

    public Todo execute(UUID todoId, UpdateTodoDto dto) {
        Todo todo = todoRepository.findTodoById(todoId)
                .orElseThrow(() -> new NotFoundException("Todo no encontrado"));

        if (!todo.getUserId().equals(authContext.getUser().getId())) {
            throw new ForbiddenException("No tienes permiso para modificar este todo");
        }

        if (dto.getTitle() != null) {
            todo.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            todo.setDescription(dto.getDescription());
        }
        if (dto.getCompleted() != null) {
            todo.setCompleted(dto.getCompleted());
        }
        if (dto.getListaId() != null) {
            todo.setListaId(dto.getListaId());
        }
        if (dto.getPriority() != null) {
            todo.setPriority(dto.getPriority());
        }
        if (dto.getDueDate() != null) {
            todo.setDueDate(dto.getDueDate());
        }

        return todoRepository.update(todo);
    }
}
