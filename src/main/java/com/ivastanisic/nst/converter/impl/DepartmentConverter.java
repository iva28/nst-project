package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class DepartmentConverter implements DTOEntityConverter<DepartmentDTO, Department> {

    @Override
    public DepartmentDTO toDTO(Department obj) {
        return (obj != null) ? new DepartmentDTO(obj.getId(), obj.getName(), obj.getShortName()) : null;
    }

    @Override
    public Department toEntity(DepartmentDTO dtoObj) {
        return (dtoObj != null) ? new Department(dtoObj.getId(), dtoObj.getName(), dtoObj.getShortName()) : null;
    }
}
