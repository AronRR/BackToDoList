package org.acme.domain.repository;

import org.acme.domain.models.Lista;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ListaRepository {
    Lista save(Lista lista);
    List<Lista> findByUserId(UUID userId);
    Optional<Lista> findListaById(UUID id);
    Lista update(Lista lista);
    void delete(UUID id);
}
