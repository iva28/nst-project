package com.ivastanisic.nst.converter;

import java.util.ArrayList;
import java.util.List;

public interface DTOEntityConverter<DTO, E>{
    DTO toDTO(E entity);
    E toEntity(DTO dtoObj);
    default  List<DTO> listToDTO(List<E> entities) {
        List<DTO> dtos = new ArrayList<DTO>();
        for (E entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    default List<E> listToEntities(List<DTO> dtos) {
        List<E> entities = new ArrayList<E>();
        for (DTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
