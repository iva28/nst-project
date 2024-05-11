package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.*;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface MemberService extends DomainService<MemberDTO, Long> {
    List<MemberDTO> getAllMembersByRole(String memberRole) throws Exception;

    List<MemberDTO> getAllMembersInDepartment(Long id) throws Exception;

    List<AcademicTitleHistoryDTO> getAllAcademicTitleHistoryForMemberId(Long id) throws Exception;

//    MemberDTO saveMember(MemberDTO memberDTO) throws Exception;

    MemberDTO updateMemberAcademicTitle(Long id, AcademicTitleDTO academicTitleDTO) throws Exception;

    List<MemberDTO> getAllByAcedemicTitle(String name) throws Exception;

    MemberDTO updateMemberRole(Long id, MemberRoleChangeDTO roleChangeDTO) throws Exception;

    MemberDTO updateMemberDepartment(MemberDepartmentChangeDTO memberDepartment) throws Exception;

    MemberDTO findDirectorForDepartment(String name) throws Exception;

    MemberDTO findSecretaryForDepartment(String name) throws Exception;

    List<MemberDTO> findAllInactiveMembers() throws Exception;
}
