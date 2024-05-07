package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivastanisic.nst.role.MemberRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleHistoryDTO implements Serializable {
    @JsonIgnore
    private Long id;
    private MemberRole role;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private MemberDTO memberDTO;
    private DepartmentDTO departmentDTO;
}
