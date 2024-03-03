package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.ScientificField;
import com.ivastanisic.nst.dto.ScientificFieldDTO;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DTOEntityConverter<ScientificFieldDTO, ScientificField> {
    @Override
    public ScientificFieldDTO toDTO(ScientificField entity) {
        return (entity != null) ? new ScientificFieldDTO(entity.getId(), entity.getName()): null;
    }

    @Override
    public ScientificField toEntity(ScientificFieldDTO dtoObj) {
        return (dtoObj != null) ? new ScientificField(dtoObj.getId(), dtoObj.getName()) : null;
    }
}
