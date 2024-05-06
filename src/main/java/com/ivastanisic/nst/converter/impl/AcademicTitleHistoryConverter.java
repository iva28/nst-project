package com.ivastanisic.nst.converter.impl;

import com.ivastanisic.nst.converter.DTOEntityConverter;
import com.ivastanisic.nst.domain.AcademicTitleHistory;
import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.role.MemberRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ivastanisic.nst.domain.Member;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class AcademicTitleHistoryConverter implements DTOEntityConverter<AcademicTitleHistoryDTO, AcademicTitleHistory> {
    @Autowired
    private final AcademicTitleConverter academicTitleConverter;
    @Autowired
    private final ScientificFieldConverter scientificFieldConverter;

    @Override
    public AcademicTitleHistoryDTO toDTO(AcademicTitleHistory at) {
        return (at != null) ?
                new AcademicTitleHistoryDTO(at.getId(),
                at.getStartDate(),
                at.getEndDate(),
                (at.getMember() == null) ? null : at.getMember().getId(),
                academicTitleConverter.toDTO(at.getAcademicTitle()),
                scientificFieldConverter.toDTO(at.getScientificField()))
                : null;
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDTO ato) {
        return (ato != null) ?
                new AcademicTitleHistory(
                ato.getId(),
                ato.getStartDate(),
                ato.getEndDate(),
                new Member(ato.getMemberDTO(), "FAKE", "FAKE", MemberRole.NORMAL,null, null, null, null, new ArrayList<>()),
                academicTitleConverter.toEntity(ato.getAcademicTitleDTO()),
                scientificFieldConverter.toEntity(ato.getScientificFieldDTO()))
                : null;
    }
}
