package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.MemberRoleHistoryDTO;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface MemberRoleHistoryService extends DomainService<MemberRoleHistoryDTO, Long> {
    List<MemberRoleHistoryDTO> getAllByDepartment(String shortName) throws Exception;

    List<MemberRoleHistoryDTO> getAllByRole(String role) throws Exception;

    List<MemberRoleHistoryDTO> findSecretaryAndDirectorForDepartment(String name) throws Exception;
}
