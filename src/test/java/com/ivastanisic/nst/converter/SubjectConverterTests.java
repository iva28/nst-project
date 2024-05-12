package com.ivastanisic.nst.converter;

import com.ivastanisic.nst.converter.impl.DepartmentConverter;
import com.ivastanisic.nst.converter.impl.SubjectConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Subject;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.dto.SubjectDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class SubjectConverterTests {
    @Autowired
    private SubjectConverter subjectConverter;
    @MockBean
    private DepartmentConverter departmentConverter;

    @Test
    public void toEntityTest() {
        Department department = new Department(1l, "Dep 1", "D1");
        Subject subject = new Subject(1l, "Subj 1", 5, department);

        DepartmentDTO departmentDTO = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO = new SubjectDTO(1l, "Subj 1", 5, departmentDTO);

        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);
        Mockito.when(departmentConverter.toEntity(departmentDTO)).thenReturn(department);

        Subject entity = subjectConverter.toEntity(subjectDTO);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(subject, entity);
    }

    @Test
    public void toDtoTest() {
        Department department = new Department(1l, "Dep 1", "D1");
        Subject subject = new Subject(1l, "Subj 1", 5, department);

        DepartmentDTO departmentDTO = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO = new SubjectDTO(1l, "Subj 1", 5, departmentDTO);

        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);
        Mockito.when(departmentConverter.toEntity(departmentDTO)).thenReturn(department);

        SubjectDTO dto = subjectConverter.toDTO(subject);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(subjectDTO, dto);
    }

    @Test
    public void toEntityTestFail() {
        Subject entity = subjectConverter.toEntity(null);
        Assertions.assertNull(entity);
    }

    @Test
    public void toDtoTestFail() {
        SubjectDTO dto = subjectConverter.toDTO(null);
        Assertions.assertNull(dto);
    }


}
