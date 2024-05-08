package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DepartmanRepositoryTests {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void saveDepartmentTest() {
        Department department = new Department(1L, "Department 1", "D1");

        Department departmentSaved = departmentRepository.save(department);

        Assertions.assertNotNull(departmentSaved);
        Assertions.assertEquals(department, departmentSaved);
    }

    @Test
    public void findDepartmentByNameTest() {
        Department department = new Department(1L, "Department 1", "D1");

        Department departmentSaved = departmentRepository.save(department);
        Assertions.assertNotNull(departmentSaved);

        Optional<Department> departmentFound = departmentRepository.findByName(departmentSaved.getName());

        Assertions.assertNotNull(departmentFound.get());
        Assertions.assertEquals(departmentSaved, departmentFound.get());
        Assertions.assertEquals(departmentSaved.getName(), departmentFound.get().getName());
    }

    @Test
    public void findDepartmentByShortNameTest() {
        Department department = new Department(1L, "Department 1", "D1");

        Department departmentSaved = departmentRepository.save(department);
        Assertions.assertNotNull(departmentSaved);

        Optional<Department> departmentFound = departmentRepository.findByShortName(departmentSaved.getShortName());

        Assertions.assertNotNull(departmentFound.get());
        Assertions.assertEquals(departmentSaved, departmentFound.get());
        Assertions.assertEquals(departmentSaved.getShortName(), departmentFound.get().getShortName());
    }


}
