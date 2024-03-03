package com.ivastanisic.nst.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScientificFieldDTO implements Serializable {

    private Long id;
    @NotNull
    private String name;
}
