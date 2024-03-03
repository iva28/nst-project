package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleConverter implements DTOEntityConverter<AcademicTitleDTO, AcademicTitle> {

    @Override
    public AcademicTitleDTO toDTO(AcademicTitle obj) {
        return (obj != null) ? new AcademicTitleDTO(obj.getId(), obj.getName()) : null;
    }

    @Override
    public AcademicTitle toEntity(AcademicTitleDTO dtoObj) {
        return (dtoObj != null) ? new AcademicTitle(dtoObj.getId(), dtoObj.getName()) : null;
    }
}
