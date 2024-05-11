package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.MemberRoleHistoryConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.MemberRoleHistory;
import com.ivastanisic.nst.dto.MemberRoleHistoryDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.MemberRoleHistoryRepository;
import com.ivastanisic.nst.role.MemberRole;
import com.ivastanisic.nst.service.abstraction.MemberRoleHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberRoleHistoryServiceImpl implements MemberRoleHistoryService {
    private final MemberRoleHistoryRepository memberRoleHistoryRepository;
    private final MemberRoleHistoryConverter memberRoleHistoryConverter;
    private final DepartmentRepository departmentRepository;


    @Override
    public MemberRoleHistoryDTO save(MemberRoleHistoryDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<MemberRoleHistoryDTO> getAll() {
        return memberRoleHistoryConverter.listToDTO(memberRoleHistoryRepository.findAll());
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public MemberRoleHistoryDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO update(MemberRoleHistoryDTO memberRoleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO updateById(Long aLong, MemberRoleHistoryDTO memberRoleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO findByName(String name) throws Exception {
        return null;
    }

    @Override
    public List<MemberRoleHistoryDTO> getAllByDepartment(String shortName) throws Exception {
        if (shortName == null) {
            throw new Exception("Department name can't be null");
        }

        Optional<Department> department = departmentRepository.findByShortName(shortName);
        if (department.isEmpty()) {
            throw new Exception("Department doesn't exist");
        }

        List<MemberRoleHistory> memberRoleHistories = memberRoleHistoryRepository.findByDepartmentShortName(shortName);
        return memberRoleHistoryConverter.listToDTO(memberRoleHistories);
    }

    @Override
    public List<MemberRoleHistoryDTO> getAllByRole(String role) throws Exception {
        if (role == null) {
            throw new Exception("Role can't be empty");
        }

        boolean roleExists = Arrays.stream(MemberRole.values()).anyMatch(
                memberRole -> memberRole == MemberRole.valueOf(role.toUpperCase()));

        if (!roleExists) {
            throw new Exception("Role doesn't exist");
        }

        List<MemberRoleHistory> roleHistories = memberRoleHistoryRepository.findByRole(
                MemberRole.valueOf(role.toUpperCase()));

        return memberRoleHistoryConverter.listToDTO(roleHistories);
    }
}
