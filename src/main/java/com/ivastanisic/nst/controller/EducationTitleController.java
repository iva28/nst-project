package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.EducationTitleDTO;
import com.ivastanisic.nst.service.abstraction.EducationTitleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-title")
@AllArgsConstructor
public class EducationTitleController {
    private final EducationTitleService educationTitleService;

    @GetMapping
    private ResponseEntity<List<EducationTitleDTO>> getAllEducationTitle() {
        List<EducationTitleDTO> educationTitleList = educationTitleService.getAll();
        return new ResponseEntity<>(educationTitleList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<EducationTitleDTO> saveEducationTitle(@Valid @RequestBody EducationTitleDTO educationTitleDTO) throws Exception {
        EducationTitleDTO savedEducationTitleDTO = educationTitleService.save(educationTitleDTO);
        return new ResponseEntity<>(savedEducationTitleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteEducationTitle(@PathVariable Long id) throws Exception {
        educationTitleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
