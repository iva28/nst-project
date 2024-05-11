package com.ivastanisic.nst.controller;

import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.service.abstraction.AcademicTitleHistoryService;
import com.sun.java.accessibility.util.GUIInitializedListener;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academic-title-history")
@AllArgsConstructor
public class AcademicTitleHistoryController {
    private final AcademicTitleHistoryService academicTitleHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AcademicTitleHistoryDTO>> getAllByMemberId(@PathVariable Long id) {
        List<AcademicTitleHistoryDTO> academicTitleHistoryDTOS = academicTitleHistoryService.getAllByMemberId(id);
        return new ResponseEntity<>(academicTitleHistoryDTOS, HttpStatus.OK);
    }
}
