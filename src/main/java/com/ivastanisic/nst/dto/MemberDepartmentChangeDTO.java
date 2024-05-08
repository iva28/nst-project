package com.ivastanisic.nst.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDepartmentChangeDTO implements Serializable {
    @NotNull
    Long memberId;
    @NotNull
    String shortName;
}
