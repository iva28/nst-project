package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.ScientificFieldDTO;
import com.ivastanisic.nst.service.abstraction.ScientificFieldService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scientific-field")
@AllArgsConstructor
public class ScientificFieldController {
    private final ScientificFieldService scientificFieldService;

    @GetMapping
    private ResponseEntity<List<ScientificFieldDTO>> getAllScientificField() {
        List<ScientificFieldDTO> scientificFieldDTOList = scientificFieldService.getAll();
        return new ResponseEntity<>(scientificFieldDTOList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ScientificFieldDTO> saveScientificField(@Valid @RequestBody ScientificFieldDTO scientificFieldDTO) throws Exception {
        ScientificFieldDTO savedScientificField = scientificFieldService.save(scientificFieldDTO);
        return new ResponseEntity<>(savedScientificField, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteScientificField(@PathVariable Long id) throws Exception {
        scientificFieldService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
