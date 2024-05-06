package com.ivastanisic.nst.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivastanisic.nst.role.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleChangeDTO implements Serializable {
    @JsonIgnore
    private Long id;
    private MemberRole role;
}
