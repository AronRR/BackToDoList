package org.acme.infrastructure.mapper;

import org.acme.domain.models.Lista;
import org.acme.infrastructure.entities.ListaEntity;

public class ListaMapper {

    private ListaMapper() {}

    public static Lista toDomain(ListaEntity entity) {
        Lista lista = new Lista();
        lista.setId(entity.getId());
        lista.setName(entity.getName());
        lista.setColor(entity.getColor());
        lista.setUserId(entity.getUserId());
        lista.setCreatedAt(entity.getCreatedAt());
        return lista;
    }

    public static ListaEntity toEntity(Lista lista) {
        ListaEntity entity = new ListaEntity();
        entity.setId(lista.getId());
        entity.setName(lista.getName());
        entity.setColor(lista.getColor());
        entity.setUserId(lista.getUserId());
        entity.setCreatedAt(lista.getCreatedAt());
        return entity;
    }
}
