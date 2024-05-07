package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.dto.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemberConverter implements DTOEntityConverter<MemberDTO, Member> {
    @Autowired
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    @Autowired
    private final DepartmentConverter departmentConverter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public MemberDTO toDTO(Member m) {
        return (m != null) ?
                new MemberDTO(
                m.getId(),
                m.getFirstName(),
                m.getLastName(),
                m.getStartDate(),
                m.getRole(),
                departmentConverter.toDTO(m.getDepartment()),
                academicTitleConverter.toDTO(m.getAcademicTitle()),
                educationTitleConverter.toDTO(m.getEducationTitle()),
                scientificFieldConverter.toDTO(m.getScientificField()),
                academicTitleHistoryConverter.listToDTO(m.getAcademicTitleHistoryList()))
                : null;
    }

    @Override
    public Member toEntity(MemberDTO dtoM) {
        return (dtoM != null) ?
                new Member(
                dtoM.getId(),
                dtoM.getFirstName(),
                dtoM.getLastName(),
                dtoM.getStartDate(),
                dtoM.getRole(),
                departmentConverter.toEntity(dtoM.getDepartmentDTO()),
                academicTitleConverter.toEntity(dtoM.getAcademicTitleDTO()),
                educationTitleConverter.toEntity(dtoM.getEducationTitleDTO()),
                scientificFieldConverter.toEntity(dtoM.getScientificFieldDTO()),
                academicTitleHistoryConverter.listToEntities(dtoM.getAcademicTitleHistoryDTOS()))
                : null;
    }
}
