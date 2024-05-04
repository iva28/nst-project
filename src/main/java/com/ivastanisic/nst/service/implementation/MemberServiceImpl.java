package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.AcademicTitleHistoryConverter;
import com.ivastanisic.nst.converter.impl.MemberConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.dto.MemberDTO;
import com.ivastanisic.nst.repository.AcademicTitleHistoryRepository;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.MemberRepository;
import com.ivastanisic.nst.role.MemberRole;
import com.ivastanisic.nst.service.abstraction.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberConverter memberConverter;
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Autowired
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;

    @Override
    public MemberDTO save(MemberDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<MemberDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public MemberDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO) throws Exception {
        return null;
    }

    @Override
    public MemberDTO updateById(Long aLong, MemberDTO memberDTO) throws Exception {
        return null;
    }

    @Override
    public List<MemberDTO> getAllMembersByRole(String memberRole) throws Exception {
        String memberRoleUpp = memberRole.toUpperCase();
        List possibleRoles = Arrays.asList(MemberRole.values());
        if (!possibleRoles.contains(MemberRole.valueOf(memberRoleUpp))) {
            throw new Exception("This member role doesn't exist");
        }
        return memberConverter.listToDTO(memberRepository.findByRole(MemberRole.valueOf(memberRoleUpp)));
    }

    @Override
    public List<MemberDTO> getAllMembersInDepartment(Long id) throws Exception {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new Exception("Department with id " + id + " doesn't exist");
        }
        System.out.println(department.get());
        return memberConverter.listToDTO(memberRepository.findByDepartmentId(id));
    }

    @Override
    public List<AcademicTitleHistoryDTO> getAllAcademicTitleHistoryForMemberId(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new Exception("Member with id "+ id+" doesn't exist");
        }
        return  academicTitleHistoryConverter.listToDTO(academicTitleHistoryRepository.findByMemberId(id));
    }
}
