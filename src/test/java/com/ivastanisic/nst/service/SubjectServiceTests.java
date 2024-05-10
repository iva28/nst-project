package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.SubjectConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Subject;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.SubjectRepository;
import com.ivastanisic.nst.service.abstraction.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class SubjectServiceTests {
    @Autowired
    private SubjectService subjectService;
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private SubjectConverter subjectConverter;

    @Test
    public void testGetAll() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);

        DepartmentDTO departmentDTO2 = new DepartmentDTO(2l, "Dep 2", "D2");
        SubjectDTO subjectDTO2 = new SubjectDTO(2l, "Subj 2", 5, departmentDTO2);

        List<SubjectDTO> subjectsDtos = List.of(subjectDTO1, subjectDTO2);

        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);

        Department department2 = new Department(2l, "Dep 2", "D2");
        Subject subject2 = new Subject(2l, "Subj 2", 5, department2);
        List<Subject> subjects = List.of(subject1, subject2);

        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);
        Mockito.when(subjectConverter.listToDTO(subjects)).thenReturn(subjectsDtos);

        List<SubjectDTO> all = subjectService.getAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all, subjectsDtos);
    }

}
