package org.acme.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.models.Todo;
import org.acme.domain.repository.TodoRepository;
import org.acme.infrastructure.entities.TodoEntity;
import org.acme.infrastructure.mapper.TodoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TodoRepositoryImpl implements TodoRepository, PanacheRepositoryBase<TodoEntity, UUID> {

    @Override
    @Transactional
    public Todo save(Todo todo) {
        TodoEntity entity = TodoMapper.toEntity(todo);
        persist(entity);
        return TodoMapper.toDomain(entity);
    }

    @Override
    public List<Todo> findByUserId(UUID userId) {
        return find("userId", userId).stream()
                .map(TodoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Todo> findTodoById(UUID id) {
        return find("id", id).firstResultOptional()
                .map(TodoMapper::toDomain);
    }

    @Override
    @Transactional
    public Todo update(Todo todo) {
        TodoEntity entity = find("id", todo.getId()).firstResult();
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.isCompleted());
        entity.setListaId(todo.getListaId());
        persist(entity);
        return TodoMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        delete("id", id);
    }

    @Override
    public List<Todo> findByListaId(UUID listaId) {
        return find("listaId", listaId).stream()
                .map(TodoMapper::toDomain)
                .toList();
    }
}
