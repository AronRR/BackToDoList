package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ForbiddenException;
import org.acme.application.dto.UpdateListaDto;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.UUID;

@ApplicationScoped
public class UpdateListaUseCase {

    private final ListaRepository listaRepository;
    private final AuthContext authContext;

    @Inject
    public UpdateListaUseCase(ListaRepository listaRepository, AuthContext authContext) {
        this.listaRepository = listaRepository;
        this.authContext = authContext;
    }

    public Lista execute(UUID listaId, UpdateListaDto dto) {
        Lista lista = listaRepository.findListaById(listaId)
                .orElseThrow(() -> new NotFoundException("Lista no encontrada"));

        if (!lista.getUserId().equals(authContext.getUser().getId())) {
            throw new ForbiddenException("No tienes permiso para modificar esta lista");
        }

        if (dto.getName() != null) {
            lista.setName(dto.getName());
        }
        if (dto.getColor() != null) {
            lista.setColor(dto.getColor());
        }

        return listaRepository.update(lista);
    }
}
