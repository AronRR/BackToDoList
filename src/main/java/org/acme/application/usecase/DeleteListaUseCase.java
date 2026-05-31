package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.UUID;

@ApplicationScoped
public class DeleteListaUseCase {

    private final ListaRepository listaRepository;
    private final AuthContext authContext;

    @Inject
    public DeleteListaUseCase(ListaRepository listaRepository, AuthContext authContext) {
        this.listaRepository = listaRepository;
        this.authContext = authContext;
    }

    public void execute(UUID listaId) {
        Lista lista = listaRepository.findListaById(listaId)
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));

        if (!lista.getUserId().equals(authContext.getUser().getId())) {
            throw new ForbiddenException("No tienes permiso para eliminar esta lista");
        }

        listaRepository.delete(listaId);
    }
}
