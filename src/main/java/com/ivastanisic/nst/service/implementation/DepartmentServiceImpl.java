package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.DepartmentConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.service.abstraction.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final DepartmentConverter departmentConverter;

    @Override
    public DepartmentDTO save(DepartmentDTO obj) throws Exception {
        Optional<Department> department_exists = departmentRepository.findByName(obj.getName());
        if (department_exists.isPresent()) {
            throw new Exception("Department with name "+ obj.getName()+" already exists");
        } else {
            Department department = departmentConverter.toEntity(obj);
            department = departmentRepository.save(department);
            return departmentConverter.toDTO(department);
        }
    }

    @Override
    public List<DepartmentDTO> getAll() {
        return departmentConverter.listToDTO(departmentRepository.findAll());
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public DepartmentDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) throws Exception {
        return null;
    }
}
