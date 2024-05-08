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

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Arrays;
import java.util.List;
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
    public void findByIdSuccessTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO departmentFound = departmentService.findById(department.getId());
        Assertions.assertNotNull(departmentFound);
        Assertions.assertEquals(departmentFound, departmentDTO);
    }

    @Test
    public void findByIdFailureTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.findById(department.getId()));
    }

    @Test
    public void deleteDepartmentSuccess() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.delete(department.getId());
        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(department.getId());
    }

    @Test
    public void deleteDepartmentFailure() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.delete(department.getId()));
    }

//    @Test
//    public void getAllDepartments() {
//        Department department1 = new Department(4l, "Department 4", "D4");
//        Department department2 = new Department(5l, "Department 5", "D5");
//
//        List<Department> departments = Arrays.asList(department1, department2);
//        System.out.println(departments);
//
//        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
//
//        DepartmentDTO departmentDTO1 = new DepartmentDTO(4l, "Department 4", "D4");
//        DepartmentDTO departmentDTO2 = new DepartmentDTO(5l, "Department 5", "D5");
//
//        Mockito.when(departmentConverter.toDTO(department1)).thenReturn(departmentDTO1);
//        Mockito.when(departmentConverter.toDTO(department2)).thenReturn(departmentDTO2);
//
//        List<DepartmentDTO> allDepartments = departmentService.getAll();
//        System.out.println(allDepartments);
//
//        for (int i = 0; i < departments.size(); i++) {
//            Assertions.assertEquals(allDepartments.get(i), departmentConverter.toDTO(departments.get(i)));
//        }
//    }

}
