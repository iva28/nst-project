package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.MemberRoleHistory;
import com.ivastanisic.nst.dto.MemberRoleHistoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemberRoleHistoryConverter implements DTOEntityConverter<MemberRoleHistoryDTO, MemberRoleHistory> {
    @Autowired
    private final MemberConverter memberConverter;
    @Autowired
    private final DepartmentConverter departmentConverter;

    @Override
    public MemberRoleHistoryDTO toDTO(MemberRoleHistory entity) {
        return (entity != null) ?
                new MemberRoleHistoryDTO(
                        entity.getId(),
                        entity.getRole(),
                        entity.getStartDate(),
                        entity.getEndDate(),
                        memberConverter.toDTO(entity.getMember()),
                        departmentConverter.toDTO(entity.getDepartment())
                        )
                : null;
    }

    @Override
    public MemberRoleHistory toEntity(MemberRoleHistoryDTO dtoObj) {
        return (dtoObj != null) ?
                new MemberRoleHistory(
                        dtoObj.getId(),
                        dtoObj.getRole(),
                        dtoObj.getStartDate(),
                        dtoObj.getEndDate(),
                        memberConverter.toEntity(dtoObj.getMemberDTO()),
                        departmentConverter.toEntity(dtoObj.getDepartmentDTO())
                )
                : null;
    }
}
