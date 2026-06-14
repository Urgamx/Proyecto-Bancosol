package com.example.proyectobancosol.mapper;

import java.util.List;

/**
 * Clase generica para convertir entidades a DTO.
 *
 * Autores:
 * - Jesus Moreno Carmona: 75%
 * - IA: 25%
 */

public abstract class MapperDTO<DTOClass, EntityClass> {


    public abstract DTOClass toDTO(EntityClass entityClass);

    public List<DTOClass> toDTOList(List<EntityClass> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}