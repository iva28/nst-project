package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.dto.MemberDTO;
import com.ivastanisic.nst.dto.MemberRoleChangeDTO;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface MemberService extends DomainService<MemberDTO, Long> {
    List<MemberDTO> getAllMembersByRole(String memberRole) throws Exception;

    List<MemberDTO> getAllMembersInDepartment(Long id) throws Exception;

    List<AcademicTitleHistoryDTO> getAllAcademicTitleHistoryForMemberId(Long id) throws Exception;

    MemberDTO saveMember(MemberDTO memberDTO) throws Exception;

    MemberDTO updateMemberAcademicTitle(Long id, AcademicTitleDTO academicTitleDTO) throws Exception;

    List<MemberDTO> getAllByAcedemicTitle(String name) throws Exception;
}
