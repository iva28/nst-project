package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.converter.impl.AcademicTitleConverter;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.service.abstraction.AcademicTitleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-title")
@AllArgsConstructor
public class AcademicTitleController {

    @Autowired
    private final AcademicTitleConverter academicTitleConverter;
    @Autowired
    private final AcademicTitleService academicTitleService;

    @GetMapping
    private ResponseEntity<List<AcademicTitleDTO>> getAllAcademicTitle() {
        List<AcademicTitleDTO> academicTitleDTOS = academicTitleService.getAll();
        return new ResponseEntity<>(academicTitleDTOS, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<AcademicTitleDTO> saveAcademicTitle(@RequestBody @Valid AcademicTitleDTO academicTitleDTO) throws Exception {
        AcademicTitleDTO savedAcademicTitleDTO = academicTitleService.save(academicTitleDTO);
        return new ResponseEntity<>(savedAcademicTitleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteAcademicTitle(@PathVariable Long id) throws Exception {
        academicTitleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
