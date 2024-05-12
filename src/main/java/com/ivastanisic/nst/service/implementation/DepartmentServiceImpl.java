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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    @Override
    public DepartmentDTO save(DepartmentDTO obj) throws Exception {
        Optional<Department> departmentExists = departmentRepository.findByNameIgnoreCase(obj.getName());
        if (departmentExists.isPresent()) {
            throw new Exception("Department with name " + obj.getName() + " already exists");
        }

        if (obj.getName() == null || obj.getName().equals("string")) {
            throw new Exception("Can't save department without name");
        }

        if (obj.getShortName() == null || obj.getShortName().equals("string")) {
            throw new Exception("Can't save department without short name");
        }

        Department department = departmentConverter.toEntity(obj);
        department = departmentRepository.save(department);
        return departmentConverter.toDTO(department);

    }

    @Override
    public List<DepartmentDTO> getAll() {
        return departmentConverter.listToDTO(departmentRepository.findAll());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Department> departmentExists = departmentRepository.findById(id);
        if (!departmentExists.isPresent())
            throw new Exception("Department with id " + id + " doesn't exist");
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDTO findById(Long id) throws Exception {
        Optional<Department> departmentExists = departmentRepository.findById(id);
        if (!departmentExists.isPresent()) {
            throw new Exception("Department with id " + id + " doesn't exist");
        }
        return departmentConverter.toDTO(departmentExists.get());
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) throws Exception {
        if (departmentDTO == null) {
            throw new Exception("Department can't be null");
        }

        if (departmentDTO.getId() == null) {
            throw new Exception("Department id can't be null");
        }

        Optional<Department> departmentExists = departmentRepository.findById(departmentDTO.getId());
        if (!departmentExists.isPresent()) {
            throw new Exception("Department with id " + departmentDTO.getId() + " doesn't exist");
        }

        Department existingDepartment = departmentExists.get();

        if (departmentDTO.getName() != null && !departmentDTO.getName().equals("")
                && !departmentDTO.getName().equals("string")) {
            existingDepartment.setName(departmentDTO.getName());
        }

        if (departmentDTO.getShortName() != null && !departmentDTO.getShortName().equals("")
                && !departmentDTO.getShortName().equals("string")) {
            existingDepartment.setShortName(departmentDTO.getShortName());
        }

        return departmentConverter.toDTO(departmentRepository.save(existingDepartment));
    }

    @Override
    public DepartmentDTO updateById(Long id, DepartmentDTO departmentDTO) throws Exception {
        Optional<Department> departmentExists = departmentRepository.findById(id);
        if (!departmentExists.isPresent()) {
            throw new Exception("Department with id " + id + " doesn't exist");
        } else {
            Department existingDepartment = departmentExists.get();
            Department departmentUpdated = departmentConverter.toEntity(departmentDTO);
            existingDepartment.setName(departmentUpdated.getName());
            existingDepartment.setShortName(departmentUpdated.getShortName());

            return departmentConverter.toDTO(departmentRepository.save(existingDepartment));
        }
    }

    @Override
    public DepartmentDTO findByName(String name) throws Exception {
        if (name == null)
            throw new Exception("Name of department can't be empty");
        return departmentConverter.toDTO(departmentRepository.findByShortNameIgnoreCase(name).get());
    }

}
