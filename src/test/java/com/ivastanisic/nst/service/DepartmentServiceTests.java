package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.DepartmentConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.service.abstraction.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class DepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentConverter departmentConverter;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void saveDepartmentSuccessTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentConverter.toEntity(departmentDTO)).thenReturn(department);
        Mockito.when(departmentRepository.findByName(department.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO savedDepartment = departmentService.save(departmentDTO);
        Assertions.assertNotNull(savedDepartment);
        Assertions.assertEquals(savedDepartment, departmentDTO);
    }

    @Test
    public void saveDepartmentFailureTest() {
        Department department = new Department(1L, "Department 1", "D1");

        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));
        Assertions.assertThrows(Exception.class, () -> departmentService.save(departmentDTO));
    }

    @Test
    public void findByIdSuccessTest() throws Exception{
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO departmentFound = departmentService.findById(department.getId());
        Assertions.assertNotNull(departmentFound);
        Assertions.assertEquals(departmentFound, departmentDTO);
    }
}
