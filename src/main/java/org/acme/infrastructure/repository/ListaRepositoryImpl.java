package org.acme.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.models.Lista;
import org.acme.domain.repository.ListaRepository;
import org.acme.infrastructure.entities.ListaEntity;
import org.acme.infrastructure.mapper.ListaMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ListaRepositoryImpl implements ListaRepository, PanacheRepositoryBase<ListaEntity, UUID> {

    @Override
    @Transactional
    public Lista save(Lista lista) {
        ListaEntity entity = ListaMapper.toEntity(lista);
        persist(entity);
        return ListaMapper.toDomain(entity);
    }

    @Override
    public List<Lista> findByUserId(UUID userId) {
        return find("userId", userId).stream()
                .map(ListaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Lista> findListaById(UUID id) {
        return find("id", id).firstResultOptional()
                .map(ListaMapper::toDomain);
    }

    @Override
    @Transactional
    public Lista update(Lista lista) {
        ListaEntity entity = find("id", lista.getId()).firstResult();
        entity.setName(lista.getName());
        entity.setColor(lista.getColor());
        persist(entity);
        return ListaMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        delete("id", id);
    }
}
