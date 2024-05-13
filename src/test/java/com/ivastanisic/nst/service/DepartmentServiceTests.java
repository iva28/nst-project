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
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@SpringBootTest
public class DepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentConverter departmentConverter;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartmentSuccessTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentConverter.toEntity(departmentDTO)).thenReturn(department);
        Mockito.when(departmentRepository.findByNameIgnoreCase(department.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO savedDepartment = departmentService.save(departmentDTO);
        Assertions.assertNotNull(savedDepartment);
        Assertions.assertEquals(savedDepartment, departmentDTO);
    }

    @Test
    public void testSaveDepartmentFailureTest() {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findByNameIgnoreCase(department.getName())).thenReturn(Optional.of(department));
        Assertions.assertThrows(Exception.class, () -> departmentService.save(departmentDTO));
    }

    @Test
    public void testFindByIdSuccessTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO departmentFound = departmentService.findById(department.getId());
        Assertions.assertNotNull(departmentFound);
        Assertions.assertEquals(departmentFound, departmentDTO);
    }

    @Test
    public void testFindByIdFailureTest() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.findById(department.getId()));
    }

    @Test
    public void testDeleteDepartmentSuccess() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.delete(department.getId());
        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(department.getId());
    }

    @Test
    public void testDeleteDepartmentFailure() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.delete(department.getId()));
    }

    @Test
    public void testGetAllDepartments() {
        Department department1 = new Department(4l, "Department 4", "D4");
        Department department2 = new Department(5l, "Department 5", "D5");

        List<Department> departments = List.of(department1, department2);

        DepartmentDTO departmentDTO1 = new DepartmentDTO(4l, "Department 4", "D4");
        DepartmentDTO departmentDTO2 = new DepartmentDTO(5l, "Department 5", "D5");

        List<DepartmentDTO> departmentsDepartmentDTOS = List.of(departmentDTO1, departmentDTO2);

        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        Mockito.when(departmentConverter.listToDTO(departments)).thenReturn(departmentsDepartmentDTOS);
        Mockito.when(departmentConverter.toDTO(department1)).thenReturn(departmentDTO1);
        Mockito.when(departmentConverter.toDTO(department2)).thenReturn(departmentDTO2);

        List<DepartmentDTO> allDepartments = departmentService.getAll();
        System.out.println(allDepartments);

        for (int i = 0; i < departments.size(); i++) {
            Assertions.assertEquals(allDepartments.get(i), departmentConverter.toDTO(departments.get(i)));
        }
    }

    @Test
    public void testDeleteDepartmentSuccessTest() throws Exception {
        Department department = new Department(4l, "Department 4", "D4");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.delete(department.getId());
        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(department.getId());
    }

    @Test
    public void testDeleteDepartmentFailureTest() throws Exception {
        Department department = new Department(4l, "Department 4", "D4");
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.delete(department.getId()));
    }

    @Test
    public void testUpdateDepartmentByIdSuccess() throws Exception {
        Long id = 1L;
        String updatedName = "Department 2";
        String updatedShortName = "D2";

        Department department = new Department(id, "Department 1", "D1");
        DepartmentDTO departmentDTOUpdate = new DepartmentDTO(null, updatedName, updatedShortName);
        Department departmentUpdate = new Department(null, updatedName, updatedShortName);

        Mockito.when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.toEntity(departmentDTOUpdate)).thenReturn(departmentUpdate);

        department.setName(updatedName);
        department.setShortName(updatedShortName);

        DepartmentDTO departmentDTONew = new DepartmentDTO(id, updatedName, updatedShortName);

        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTONew);

        DepartmentDTO updatedDepartment = departmentService.updateById(id, departmentDTOUpdate);
        Assertions.assertNotNull(updatedDepartment);
        Assertions.assertEquals(departmentDTOUpdate.getName(), updatedDepartment.getName());
        Assertions.assertEquals(departmentDTOUpdate.getShortName(), updatedDepartment.getShortName());
    }

    @Test
    public void testUpdateDepartmentByIdFailure() throws Exception {
        Long id = 1L;
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.updateById(id, departmentDTO));
    }

    @Test
    public void testFindDepartmentByNameSuccess() throws Exception {
        String name = "Department 1";
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findByShortNameIgnoreCase(name)).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO byName = departmentService.findByName(name);
        Assertions.assertNotNull(byName);
        Assertions.assertEquals(departmentDTO.getName(), byName.getName());
    }

    @Test
    public void testFindDepartmentByNameFailure() {
        Assertions.assertThrows(Exception.class, () -> departmentService.findByName(null));
    }

//    @Test
//    public void testUpdateDepartmentNotImplementedYet() throws Exception {
//        DepartmentDTO update = departmentService.update(new DepartmentDTO(1l, null, null));
//        Assertions.assertNull(update);
//    }
//
    @Test
    public void testUpdateDepartmentSuccess() throws Exception {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");

        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO update = departmentService.update(departmentDTO);
        Assertions.assertNotNull(update);
        Assertions.assertEquals(departmentDTO, update);
    }

    @Test
    public void testUpdateDepartmentEmpty() {
        Assertions.assertThrows(Exception.class, () -> departmentService.update(null));
    }

    @Test
    public void testUpdateDepartmentNullId() {
        Assertions.assertThrows(Exception.class, () -> departmentService.update(new DepartmentDTO(null, null, null)));
    }

    @Test
    public void testSaveDepartmentWithoutName() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, null, "D1");
        Assertions.assertThrows(Exception.class, () -> departmentService.save(departmentDTO));
    }

    @Test
    public void testSaveDepartmentWithoutShortName() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "d1", null);
        Assertions.assertThrows(Exception.class, () -> departmentService.save(departmentDTO));
    }

    @Test
    public void testFindDepartmentNonExisting() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");
        Mockito.when(departmentRepository.findById(departmentDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> departmentService.findById(departmentDTO.getId()));
    }

}
