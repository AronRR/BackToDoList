package org.acme.application.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;
import org.acme.infrastructure.security.AuthContext;

import java.util.List;

@ApplicationScoped
public class GetUserListasUseCase {

    private final ListaRepository listaRepository;
    private final AuthContext authContext;

    @Inject
    public GetUserListasUseCase(ListaRepository listaRepository, AuthContext authContext) {
        this.listaRepository = listaRepository;
        this.authContext = authContext;
    }

    public List<Lista> execute() {
        return listaRepository.findByUserId(authContext.getUser().getId());
    }
}
