package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.service.implementation.SubjectServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {

    @Autowired
    private final SubjectServiceImpl subjectService;

    @GetMapping
    private ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<SubjectDTO> saveSubject(@Valid @RequestBody SubjectDTO subjectDTO) throws Exception {
        SubjectDTO savedSubjectDTO = subjectService.save(subjectDTO);
        return new ResponseEntity<>(savedSubjectDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id) throws Exception {
        SubjectDTO subjectDTO = subjectService.findById(id);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteSubject(@PathVariable Long id) throws Exception {
        subjectService.delete(id);
        return new ResponseEntity<>("Subject deleted", HttpStatus.OK);
    }


}