package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.dto.CreateListaDto;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;
import org.acme.infrastructure.security.AuthContext;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CreateListaUseCase {

    private final ListaRepository listaRepository;
    private final AuthContext authContext;

    @Inject
    public CreateListaUseCase(ListaRepository listaRepository, AuthContext authContext) {
        this.listaRepository = listaRepository;
        this.authContext = authContext;
    }

    public Lista execute(CreateListaDto dto) {
        Lista lista = new Lista();
        lista.setId(UUID.randomUUID());
        lista.setName(dto.getName());
        lista.setColor(dto.getColor());
        lista.setCreatedAt(LocalDateTime.now());
        lista.setUserId(authContext.getUser().getId());
        return listaRepository.save(lista);
    }
}
