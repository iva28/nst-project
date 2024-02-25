package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.Subject;
import com.ivastanisic.nst.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubjectConverter implements DTOEntityConverter<SubjectDTO, Subject> {
    @Autowired
    private final DepartmentConverter departmentConverter;

    @Override
    public SubjectDTO toDTO(Subject entity) {
        return (entity != null) ? new SubjectDTO(entity.getId(), entity.getName(), entity.getEspb(),
                departmentConverter.toDTO(entity.getDepartment())) : null;
    }

    @Override
    public Subject toEntity(SubjectDTO dtoObj) {
        return (dtoObj != null) ? new Subject(dtoObj.getId(), dtoObj.getName(), dtoObj.getEspb(),
                departmentConverter.toEntity(dtoObj.getDepartmentDTO())) : null;
    }
}
