package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.*;
import com.ivastanisic.nst.domain.*;
import com.ivastanisic.nst.dto.*;
import com.ivastanisic.nst.repository.*;
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
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final DepartmentRepository departmentRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final DepartmentService departmentService;
    private final AcademicTitleService academicTitleService;
    private final EducationTitleService educationTitleService;
    private final ScientificFieldService scientificFieldService;
    private final AcademicTitleHistoryService academicTitleHistoryService;
    private final AcademicTitleRepository academicTitleRepository;
    private final MemberRoleHistoryRepository memberRoleHistoryRepository;

    @Override
    @Transactional
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        if (memberDTO == null) {
            throw new Exception("You can't save member without data");
        }
        if (memberDTO.getRole() == MemberRole.DIRECTOR || memberDTO.getRole() == MemberRole.SECRETARY || memberDTO.getRole() == MemberRole.INACTIVE) {
            throw new Exception("This is only for saving member with normal roles");
        }

        if (memberDTO.getFirstName().equals("") || memberDTO.getFirstName().equals("string")) {
            throw new Exception("Member must have first name");
        }

        if (memberDTO.getLastName().equals("") || memberDTO.getLastName().equals("string")) {
            throw new Exception("Member must have last name");
        }


        Optional<Member> memberExists = memberRepository.findByFirstNameAndLastNameAndDepartmentShortName(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getDepartmentDTO().getShortName());

        if (!memberExists.isEmpty()) {
            throw new Exception("Member already exists");
        }

        if (memberDTO.getStartDate().isBefore(LocalDate.now())) {
            throw new Exception("Member start date can't be before today's date");
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
    public List<MemberDTO> getAll() {
//        return memberConverter.listToDTO(memberRepository.findAll());
        return memberConverter.listToDTO(memberRepository.findAllActive());
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id can't be null");
        }

        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new Exception("Member with id " + id + " doesn't exist. You can't delete it");
        }

//        if (member.get().getRole() == MemberRole.SECRETARY || member.get().getRole() == MemberRole.DIRECTOR) {
//            throw new Exception("You can't delete secretary or director. Fill their roles first");
//        }

        final Member memberDelete = member.get();
        if (memberDelete.getRole() == MemberRole.DIRECTOR
                || memberDelete.getRole() == MemberRole.SECRETARY) {

            MemberRoleHistory memberRoleHistory = new MemberRoleHistory(
                    null,
                    memberDelete.getRole(),
                    memberDelete.getStartDate(),
                    LocalDate.now(),
                    memberDelete,
                    memberDelete.getDepartment()
            );

            memberRoleHistoryRepository.save(memberRoleHistory);
        }
        memberDelete.setRole(MemberRole.INACTIVE);
//        memberRepository.deleteById(id);
        memberRepository.save(memberDelete);
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

        if (member.get().getRole() == MemberRole.INACTIVE) {
            throw new Exception("Member is inactive, you can't update their academic title");
        }

        Optional<AcademicTitle> academicTitleCheck = academicTitleRepository.findByNameIgnoreCase(academicTitleDTO.getName());
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
    public List<MemberDTO> getAllByAcedemicTitle(String name) throws Exception {
        if (name == null) {
            throw new Exception("Academic title can't be null");
        }

        Optional<AcademicTitle> academicTitle = academicTitleRepository.findByNameIgnoreCase(name);
        if (academicTitle.isEmpty()) {
            throw new Exception("Academic title doesn't exist");
        }

        return memberConverter.listToDTO(memberRepository.findByAcademicTitleName(name));
    }

    @Override
    @Transactional
    public MemberDTO updateMemberRole(Long id, MemberRoleChangeDTO roleChangeDTO) throws Exception {
        if (id == null) {
            throw new Exception("Member id can't be null");
        }

        Optional<Member> memberExists = memberRepository.findById(id);
        if (memberExists.isEmpty()) {
            throw new Exception("Member doesn't exist");
        }

        if (memberExists.get().getRole() == MemberRole.INACTIVE) {
            throw new Exception("Member is inactive. Activate it before assiging director or secretary roles");
        }

        if (roleChangeDTO == null) {
            throw new Exception("Role to which the member role will change, can't be null");
        }

        boolean roles = Arrays.stream(MemberRole.values())
                .anyMatch(memberRole -> memberRole.name().equalsIgnoreCase(roleChangeDTO.getRole()));

        if (!roles) {
            throw new Exception("Role " + roleChangeDTO + " doesn't exist");
        }

        if (roleChangeDTO.getRole() == MemberRole.NORMAL.toString()) {
            throw new Exception("This endpoint is for handling director/secretary changes only");
        }

        final Member member = memberExists.get();
        Optional<Member> memberWithRole = memberRepository.findByRoleAndDepartmentShortName(
                MemberRole.valueOf(roleChangeDTO.getRole().toUpperCase()),
                member.getDepartment().getShortName());

        MemberDTO opposite = findById(id);
        Member oldRoleHolder = null;

        if (!memberWithRole.isEmpty()) {
            oldRoleHolder = memberWithRole.get();
        } else if (opposite.getRole() == MemberRole.SECRETARY
                || opposite.getRole() == MemberRole.DIRECTOR) {
            oldRoleHolder = memberConverter.toEntity(opposite);
        }

        if (oldRoleHolder != null) {
            MemberRoleHistory history = new MemberRoleHistory(
                    null,
                    MemberRole.valueOf(roleChangeDTO.getRole().toUpperCase()),
                    oldRoleHolder.getStartDate(),
                    LocalDate.now(),
                    oldRoleHolder,
                    oldRoleHolder.getDepartment()
            );

            memberRoleHistoryRepository.save(history);

            oldRoleHolder.setRole(MemberRole.NORMAL);
            oldRoleHolder.setStartDate(LocalDate.now());

//            saving old role holder
            memberRepository.save(oldRoleHolder);
        }

        member.setRole(MemberRole.valueOf(roleChangeDTO.getRole().toUpperCase()));
        member.setStartDate(LocalDate.now());

        return memberConverter.toDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO updateMemberDepartment(MemberDepartmentChangeDTO memberDepartment) throws Exception {
        if (memberDepartment == null) {
            throw new Exception("Member and department both can't be null");
        }

        Optional<Member> memberExists = memberRepository.findById(memberDepartment.getMemberId());
        if (memberExists.isEmpty()) {
            throw new Exception("Member doesn't exist");
        }

        final Member member = memberExists.get();
        if (member.getRole() == MemberRole.DIRECTOR || member.getRole() == MemberRole.SECRETARY) {
            throw new Exception("You can't change department for director or secretary. Change their roles first");
        }

        if (member.getRole() == MemberRole.INACTIVE) {
            throw new Exception("This member is inactive. You can't change their department");
        }

        Optional<Department> departmentExists = departmentRepository.findByShortNameIgnoreCase(memberDepartment.getShortName());
        if (departmentExists.isEmpty()) {
            throw new Exception("Department doesn't exist");
        }

        final Department department = departmentExists.get();
        member.setDepartment(department);
        member.setStartDate(LocalDate.now());

        return memberConverter.toDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO findDirectorForDepartment(String name) throws Exception {
        if (name == null) {
            throw new Exception("Department name can't be null");
        }

        Optional<Department> department = departmentRepository.findByShortNameIgnoreCase(name);
        if (department.isEmpty()) {
            throw new Exception("Department doesn't exist");
        }
        Optional<Member> director = memberRepository.findByRoleAndDepartmentShortName(MemberRole.DIRECTOR, name);
        if (director.isEmpty()) {
            throw new Exception("There is no director for " + name + " department");
        }

        return memberConverter.toDTO(director.get());
    }

    @Override
    public MemberDTO findSecretaryForDepartment(String name) throws Exception {
        if (name == null) {
            throw new Exception("Department name can't be null");
        }

        Optional<Department> department = departmentRepository.findByShortNameIgnoreCase(name);
        if (department.isEmpty()) {
            throw new Exception("Department doesn't exist");
        }

        Optional<Member> secretary = memberRepository.findByRoleAndDepartmentShortName(MemberRole.SECRETARY, name);
        return memberConverter.toDTO(secretary.get());
    }

    @Override
    public List<MemberDTO> findAllInactiveMembers() throws Exception {
        return memberConverter.listToDTO(memberRepository.findAllInactive());
    }

    @Override
    @Transactional
    public MemberDTO saveDirectorOrSecretary(MemberDTO memberDTO) throws Exception {
        if (memberDTO == null) {
            throw new Exception("You can't save director without data");
        }

        if (memberDTO.getRole() == MemberRole.NORMAL
                || memberDTO.getRole() == MemberRole.INACTIVE) {
            throw new Exception("This is only for saving member with director or secretary roles");
        }

        if (memberDTO.getFirstName().equals("") || memberDTO.getFirstName().equals("string")) {
            throw new Exception("Director must have first name");
        }

        if (memberDTO.getLastName().equals("") || memberDTO.getLastName().equals("string")) {
            throw new Exception("Director must have last name");
        }

        Optional<Member> memberExists = memberRepository.findByFirstNameAndLastNameAndDepartmentShortName(
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getDepartmentDTO().getShortName());

        if (!memberExists.isEmpty()) {
            throw new Exception("Member already exists, go to different endpoint to update role");
        }

        if (memberDTO.getStartDate().isBefore(LocalDate.now())) {
            throw new Exception("Member start date can't be before today's date");
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

        Optional<Member> memberWithRole = memberRepository.findByRoleAndDepartmentShortName(
                MemberRole.valueOf(memberDTO.getRole().toString().toUpperCase()),
                memberDTO.getDepartmentDTO().getShortName());

        if (memberWithRole.isPresent()) {
            Member oldDirector = memberWithRole.get();

            MemberRoleHistory history = new MemberRoleHistory(
                    null,
                    MemberRole.valueOf(memberDTO.getRole().toString().toUpperCase()),
                    oldDirector.getStartDate(),
                    LocalDate.now(),
                    oldDirector,
                    oldDirector.getDepartment()
            );

            memberRoleHistoryRepository.save(history);

            oldDirector.setRole(MemberRole.NORMAL);
            oldDirector.setStartDate(LocalDate.now());

            memberRepository.save(oldDirector);
        }

        memberDTO.setRole(MemberRole.DIRECTOR);
        memberDTO.setStartDate(LocalDate.now());

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
