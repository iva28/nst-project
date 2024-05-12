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

        if (obj.getName() == null || obj.getName().equals("") || obj.getName().equals("string")) {
            throw new Exception("Subject must have a name");
        }

        if (obj.getEspb() < 6 || obj.getEspb() > 10) {
            throw new Exception("Espb can't be less than 6 or more than 10");
        }


        if (obj.getDepartmentDTO().getShortName().equals("string")) {
            throw new Exception("Subject must belong to a department");
        }
        Optional<Department> department = departmentRepository.
                findByShortNameIgnoreCase(obj.getDepartmentDTO().getShortName().toUpperCase());

        System.out.println(department);
        if (department.isEmpty()) {
            throw new Exception("You can't assign subject to department that doesn't exist");
        }

        Optional<Subject> subject = subjectRepository.findByName(obj.getName());
        if (subject.isPresent())
            throw new Exception("Subject with this id already exists");

        final Subject saveSubject = subjectConverter.toEntity(obj);
        saveSubject.setDepartment(department.get());

        System.out.println(saveSubject);
        return subjectConverter.toDTO(subjectRepository.save(saveSubject));
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
        if (subjectDTO == null) {
            throw new Exception("Subject can't be null");
        }

        if (subjectDTO.getId() == null) {
            throw new Exception("Enter subject id");
        }

        Optional<Subject> subjectFound = subjectRepository.findById(subjectDTO.getId());
        if (subjectFound.isEmpty()) {
            throw new Exception("You can't update subject that doesn't exist");
        }

        final Subject subject = subjectFound.get();

        if (subjectDTO.getEspb() >= 6 && subjectDTO.getEspb() <= 10) {
//            throw new Exception("Espb can't be less than 6 or more than 10");
            subject.setEspb(subjectDTO.getEspb());
        }

        if (subjectDTO.getName() != null
                && !subjectDTO.getName().equals("")
                && !subjectDTO.getName().equals("string")) {
            subject.setName(subjectDTO.getName());
        }

        return subjectConverter.toDTO(subjectRepository.save(subject));
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
        Optional<Department> department = departmentRepository.findByShortNameIgnoreCase(name);
        if (department.isEmpty()) {
            throw new Exception("Department with name " + name + " doesn't exist");
        }

        return subjectConverter.listToDTO(subjectRepository.findByDepartmentShortName(name));
    }
}
