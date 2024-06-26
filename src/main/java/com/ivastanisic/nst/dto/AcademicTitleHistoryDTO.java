package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicTitleHistoryDTO implements Serializable {
    @JsonIgnore
    private Long id;
    @NotNull
    private LocalDate startDate;
//    @NotNull
    private LocalDate endDate;
    private Long memberDTO;
    private AcademicTitleDTO academicTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
}
