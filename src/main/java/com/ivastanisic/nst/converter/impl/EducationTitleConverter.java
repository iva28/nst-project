package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.EducationTitle;
import com.ivastanisic.nst.dto.EducationTitleDTO;
import org.springframework.stereotype.Component;

@Component
public class EducationTitleConverter implements DTOEntityConverter<EducationTitleDTO, EducationTitle> {
    @Override
    public EducationTitleDTO toDTO(EducationTitle entity) {
        return (entity != null) ? new EducationTitleDTO(entity.getId(), entity.getName()) : null;
    }

    @Override
    public EducationTitle toEntity(EducationTitleDTO dtoObj) {
        return (dtoObj != null) ? new EducationTitle(dtoObj.getId(), dtoObj.getName()) : null;
    }
}
