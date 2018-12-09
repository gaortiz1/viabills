package com.mycompany.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<DTO, Entity> {

    DTO makeDTO(Entity entity);

    Entity makeEntity(DTO dto);

    default List<DTO> makeDtos(List<Entity> entities) {
        return entities.stream().map(this::makeDTO).collect(Collectors.toList());
    }

    default List<Entity> makeEntities(List<DTO> entities) {
        return entities.stream().map(this::makeEntity).collect(Collectors.toList());
    }

}
