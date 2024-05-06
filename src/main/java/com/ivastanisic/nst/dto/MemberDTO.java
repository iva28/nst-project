package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivastanisic.nst.role.MemberRole;
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
    @JsonIgnore
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private MemberRole role;
    private DepartmentDTO departmentDTO;
    private AcademicTitleDTO academicTitleDTO;
    private EducationTitleDTO educationTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
    @JsonIgnore
    private List<AcademicTitleHistoryDTO> academicTitleHistoryDTOS;
}
