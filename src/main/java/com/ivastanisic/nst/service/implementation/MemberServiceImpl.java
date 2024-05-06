package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.*;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.dto.*;
import com.ivastanisic.nst.repository.AcademicTitleHistoryRepository;
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
            DepartmentDTO savedDepartmentDTO = departmentService.save(memberDTO.getDepartmentDTO());
            memberDTO.setDepartmentDTO(savedDepartmentDTO);
            System.out.println("No existing department, saved " + savedDepartmentDTO);
        } else {
            System.out.println("Department exists " + departmentDTO);
            memberDTO.setDepartmentDTO(departmentDTO);
        }

        AcademicTitleDTO academicTitleDTO = academicTitleService.findByName(memberDTO.getAcademicTitleDTO().getName());
        if (academicTitleDTO == null) {
            AcademicTitleDTO savedAcademicTitleDTO = academicTitleService.save(memberDTO.getAcademicTitleDTO());
            memberDTO.setAcademicTitleDTO(savedAcademicTitleDTO);
            System.out.println("No existing academic title, saved " + savedAcademicTitleDTO);
        } else {
            System.out.println("Academic title exists " + academicTitleDTO);
            memberDTO.setAcademicTitleDTO(academicTitleDTO);
        }

        EducationTitleDTO educationTitleDTO = educationTitleService.findByName(memberDTO.getEducationTitleDTO().getName());
        if (educationTitleDTO == null) {
            EducationTitleDTO savedEducationTitleDTO = educationTitleService.save(memberDTO.getEducationTitleDTO());
            memberDTO.setEducationTitleDTO(savedEducationTitleDTO);
            System.out.println("No existing education tile, saved " + savedEducationTitleDTO);
        } else {
            System.out.println("Education title exists " + educationTitleDTO);
            memberDTO.setEducationTitleDTO(educationTitleDTO);
        }

        ScientificFieldDTO scientificFieldDTO = scientificFieldService.findByName(memberDTO.getScientificFieldDTO().getName());
        if (scientificFieldDTO == null) {
            ScientificFieldDTO savedScientificFieldDTO = scientificFieldService.save(memberDTO.getScientificFieldDTO());
            memberDTO.setScientificFieldDTO(savedScientificFieldDTO);
            System.out.println("No existing scientific field, saved " + savedScientificFieldDTO);
        } else {
            System.out.println("Scientific field exists " + scientificFieldDTO);
            memberDTO.setScientificFieldDTO(scientificFieldDTO);
        }

        memberDTO.setAcademicTitleHistoryDTOS(new ArrayList<>());
        System.out.println(memberDTO);
        System.out.println(memberConverter.toEntity(memberDTO));

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
}
