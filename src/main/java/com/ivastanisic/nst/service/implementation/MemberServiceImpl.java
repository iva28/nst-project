package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.*;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.domain.AcademicTitleHistory;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.dto.*;
import com.ivastanisic.nst.repository.AcademicTitleHistoryRepository;
import com.ivastanisic.nst.repository.AcademicTitleRepository;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.MemberRepository;
import com.ivastanisic.nst.role.MemberRole;
import com.ivastanisic.nst.service.abstraction.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private final DepartmentService departmentService;
    @Autowired
    private final AcademicTitleService academicTitleService;
    @Autowired
    private final EducationTitleService educationTitleService;
    @Autowired
    private final ScientificFieldService scientificFieldService;
    @Autowired
    private final AcademicTitleHistoryService academicTitleHistoryService;

    @Autowired
    private final AcademicTitleRepository academicTitleRepository;

    @Override
    public MemberDTO save(MemberDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<MemberDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id can't be null");
        }

        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new Exception("Member with id " + id +" doesn't exist. You can't delete it");
        }

        if (member.get().getRole() == MemberRole.SECRETARY || member.get().getRole() == MemberRole.DIRECTOR) {
            throw new Exception("You can't delete secretary or director. Fill their roles first");
        }

        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Member id can't be null");
        }

        return memberConverter.toDTO(memberRepository.findById(id).get());
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
    public MemberDTO findByName(String name) throws Exception {
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
            throw new Exception("Member with id " + id + " doesn't exist");
        }
        return academicTitleHistoryConverter.listToDTO(academicTitleHistoryRepository.findByMemberId(id));
    }

    @Transactional
    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) throws Exception {
        if (memberDTO == null) {
            throw new Exception("You can't save member without data");
        }
        Optional<Member> memberExists = memberRepository.findByFirstNameAndLastNameAndDepartmentShortName(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getDepartmentDTO().getShortName());

        if (!memberExists.isEmpty()) {
            throw new Exception("Member already exists");
        }

        DepartmentDTO departmentDTO = departmentService.findByName(memberDTO.getDepartmentDTO().getShortName());
        if (departmentDTO == null) {
            throw new Exception("Department doesn't exist");
        } else {
            memberDTO.setDepartmentDTO(departmentDTO);
        }

        AcademicTitleDTO academicTitleDTO = academicTitleService.findByName(memberDTO.getAcademicTitleDTO().getName());
        if (academicTitleDTO == null) {
            throw new Exception("Academic title doesn't exist");
        } else {
            memberDTO.setAcademicTitleDTO(academicTitleDTO);
        }

        EducationTitleDTO educationTitleDTO = educationTitleService.findByName(memberDTO.getEducationTitleDTO().getName());
        if (educationTitleDTO == null) {
            throw new Exception("Education title doesn't exist");
        } else {
            memberDTO.setEducationTitleDTO(educationTitleDTO);
        }

        ScientificFieldDTO scientificFieldDTO = scientificFieldService.findByName(memberDTO.getScientificFieldDTO().getName());
        if (scientificFieldDTO == null) {
            throw new Exception("Scientific field doesn't exist");
        } else {
            memberDTO.setScientificFieldDTO(scientificFieldDTO);
        }

        memberDTO.setAcademicTitleHistoryDTOS(new ArrayList<>());

        //        academic title history save
        Member savedMember = memberRepository.save(memberConverter.toEntity(memberDTO));
        academicTitleHistoryService.save(new AcademicTitleHistoryDTO(
                -1L,
                LocalDate.now(),
                null,
                savedMember.getId(),
                memberDTO.getAcademicTitleDTO(),
                memberDTO.getScientificFieldDTO()));
        return memberConverter.toDTO(savedMember);
    }

    @Override
    @Transactional
    public MemberDTO updateMemberAcademicTitle(Long id, AcademicTitleDTO academicTitleDTO) throws Exception {
        if (id == null) {
            throw new Exception("Id of member for updating their academic title can't be null");
        }

        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new Exception("Member doesn't exist");
        }

        Optional<AcademicTitle> academicTitleCheck = academicTitleRepository.findByName(academicTitleDTO.getName());
        if (academicTitleCheck.isEmpty()) {
            throw new Exception("Input valid academic title");
        }

        Member memberToUpdate = member.get();
        final LocalDate endDate = LocalDate.now();

        AcademicTitleHistory history = new AcademicTitleHistory(
                null,
                memberToUpdate.getStartDate(),
                endDate,
                memberToUpdate,
                memberToUpdate.getAcademicTitle(),
                memberToUpdate.getScientificField()
        );

        academicTitleHistoryRepository.save(history);

        memberToUpdate.setAcademicTitle(academicTitleCheck.get());
        memberToUpdate.setStartDate(endDate);

        return memberConverter.toDTO(memberRepository.save(memberToUpdate));
    }

    @Override
    public List<MemberDTO> getAllByAcedemicTitle(AcademicTitleDTO academicTitleDTO) throws Exception {
        return null;
    }


}
