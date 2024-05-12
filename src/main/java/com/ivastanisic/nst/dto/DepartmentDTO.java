package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO implements Serializable {

//    @JsonIgnore
    private Long id;
//    @NotNull
    private String name;
    private String shortName;

}
