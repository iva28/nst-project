package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.SubjectConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Subject;
import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.SubjectRepository;
import com.ivastanisic.nst.service.abstraction.SubjectService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectConverter subjectConverter;

    @Override
    @Transactional
    public SubjectDTO save(SubjectDTO obj) throws Exception {
        if (obj == null) {
            throw new Exception("Subject can;t be empty");
        }

        if (obj.getDepartmentDTO().getId() == null) {
            throw new Exception("Subject must belong to a department");
        }

        Optional<Department> department = departmentRepository.findById(obj.getDepartmentDTO().getId());
        if (department.isEmpty()) {
            departmentRepository.save(subjectConverter.toEntity(obj).getDepartment());
        }

        Optional<Subject> subject = subjectRepository.findById(obj.getId());
        if (subject.isPresent())
            throw new Exception("Subject with this id already exists");

        return subjectConverter.toDTO(subjectRepository.save(subjectConverter.toEntity(obj)));
    }

    @Override
    public List<SubjectDTO> getAll() {
        return subjectConverter.listToDTO(subjectRepository.findAll());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty())
            throw new Exception("Subject with id " + id + " doesn't exist, you can not delete it");

        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDTO findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty())
            throw new Exception("Subject with id " + id + " doesn't exist");

        return subjectConverter.toDTO(subject.get());
    }

    @Override
    public SubjectDTO update(SubjectDTO subjectDTO) throws Exception {
        return null;
    }

    @Override
    public SubjectDTO updateById(Long aLong, SubjectDTO subjectDTO) throws Exception {
        return null;
    }

    @Override
    public SubjectDTO findByName(String name) throws Exception {
        return null;
    }

    @Override
    public List<SubjectDTO> findByDepartmentName(String name) throws Exception {
        Optional<Department> department = departmentRepository.findByShortName(name);
        if (department.isEmpty()) {
            throw new Exception("Department with name "+ name+" doesn't exist");

        }

        return subjectConverter.listToDTO(subjectRepository.findByDepartmentShortName(name));
    }
}
