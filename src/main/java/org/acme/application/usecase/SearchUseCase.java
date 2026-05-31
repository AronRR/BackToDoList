package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.dto.SearchResultDto;
import org.acme.domain.models.Lista;
import org.acme.domain.models.Todo;
import org.acme.domain.repository.ListaRepository;
import org.acme.domain.repository.TodoRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SearchUseCase {

    private final TodoRepository todoRepository;
    private final ListaRepository listaRepository;
    private final AuthContext authContext;

    @Inject
    public SearchUseCase(TodoRepository todoRepository, ListaRepository listaRepository, AuthContext authContext) {
        this.todoRepository = todoRepository;
        this.listaRepository = listaRepository;
        this.authContext = authContext;
    }

    public SearchResultDto execute(String query) {
        UUID userId = authContext.getUser().getId();
        String lowerQuery = query.toLowerCase();

        List<Todo> todos = todoRepository.findByUserId(userId).stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(lowerQuery))
                .toList();

        List<Lista> listas = listaRepository.findByUserId(userId).stream()
                .filter(lista -> lista.getName().toLowerCase().contains(lowerQuery))
                .toList();

        return new SearchResultDto(listas, todos);
    }
}
