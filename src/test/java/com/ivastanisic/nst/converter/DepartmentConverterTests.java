package com.ivastanisic.nst.converter;

import com.ivastanisic.nst.converter.impl.DepartmentConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class DepartmentConverterTests {
    @Autowired
    private DepartmentConverter departmentConverter;

    @Test
    public void toEntityTest() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Department 1", "D1");
        Department department = departmentConverter.toEntity(departmentDTO);
        Assertions.assertEquals(departmentDTO.getId(), department.getId());
        Assertions.assertEquals(departmentDTO.getName(), department.getName());
        Assertions.assertEquals(departmentDTO.getShortName(), department.getShortName());
    }

    @Test
    public void toDtoTest() {
        Department department = new Department(1L, "Department 1", "D1");
        DepartmentDTO departmentDTO = departmentConverter.toDTO(department);
        Assertions.assertEquals(departmentDTO.getId(), department.getId());
        Assertions.assertEquals(departmentDTO.getName(), department.getName());
        Assertions.assertEquals(departmentDTO.getShortName(), department.getShortName());
    }

    @Test
    public void toEntityTestFail() {
        Department entity = departmentConverter.toEntity(null);
        Assertions.assertNull(entity);
    }

    @Test
    public void toDtoTestFail() {
        DepartmentDTO dto = departmentConverter.toDTO(null);
        Assertions.assertNull(dto);
    }
}
