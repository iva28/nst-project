package com.ivastanisic.nst.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements Serializable {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private DepartmentDTO departmentDTO;
    private AcademicTitleDTO academicTitleDTO;
    private EducationTitleDTO educationTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
    private List<AcademicTitleHistoryDTO> academicTitleHistoryDTOS;

}
