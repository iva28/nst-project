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
                departmentConverter.toDTO(m.getDepartment()),
                academicTitleConverter.toDTO(m.getAcademicTitle()),
                educationTitleConverter.toDTO(m.getEducationTitle()),
                scientificFieldConverter.toDTO(m.getScientificField()),
                academicTitleHistoryConverter.listToDTO(m.getAcademicTitleHistoryList()))
                : null;
    }

    @Override
    public Member toEntity(MemberDTO dtoM) {
        return null;
//        return (dtoM != null) ? new Member(dtoM.getId(), dtoM.getFirstName(),
//                dtoM.getLastName(), dtoM.getDepartmentDTO(), dtoM.getAcademicTitleDTO(), dtoM.getEducationTitleDTO(),
//                dtoM.getScientificFieldDTO(), listToEntities(dtoM.getAcademicTitleHistoryDTOS())) : null;
    }
}
