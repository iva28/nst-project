package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.service.abstraction.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;

    @GetMapping("/all")
    private ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) throws Exception{
        DepartmentDTO departmentDTO_saved = departmentService.save(departmentDTO);
        return new ResponseEntity<DepartmentDTO>(departmentDTO_saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteDepartment(@PathVariable Long id) throws Exception {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<DepartmentDTO> findDepartmentById(@PathVariable Long id) throws  Exception{
        return new ResponseEntity<DepartmentDTO>(departmentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDTO departmentDTO) throws Exception{
        DepartmentDTO departmentUpdated = departmentService.updateById(id, departmentDTO);
        return new ResponseEntity<DepartmentDTO>(departmentUpdated, HttpStatus.ACCEPTED);
    }

}
