package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivastanisic.nst.domain.Department;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO implements Serializable {
    @JsonIgnore
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int espb;
    //    @NotNull
    private DepartmentDTO departmentDTO;

}
