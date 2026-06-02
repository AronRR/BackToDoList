package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;

import java.util.UUID;

@ApplicationScoped
public class GetListaByIdUseCase {

    private final ListaRepository listaRepository;

    @Inject
    public GetListaByIdUseCase(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public Lista execute(UUID id) {
        return listaRepository.findListaById(id)
                .orElseThrow(() -> new NotFoundException("Lista not found"));
    }
}
