package com.ivastanisic.nst.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.service.abstraction.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.List;
import java.util.Optional;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTests {
    @MockBean
    private SubjectService subjectService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSubjects() throws Exception {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);
        SubjectDTO subjectDTO2 = new SubjectDTO(2l, "Subj 2", 6, departmentDTO1);

        List<SubjectDTO> subjectDTOS = List.of(subjectDTO1, subjectDTO2);
        Mockito.when(subjectService.getAll()).thenReturn(subjectDTOS);

        MvcResult result = mockMvc.perform(get("/subject/all"))
                .andExpect(status().isOk()).andReturn();

        List<SubjectDTO> subjectResult = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<SubjectDTO>>() {
                });

        Assertions.assertNotNull(subjectResult);
        Assertions.assertEquals(subjectDTOS.get(0).getName(), subjectResult.get(0).getName());
        Mockito.verify(subjectService, Mockito.times(1)).getAll();
    }

    @Test
    public void testSaveSubjectSuccess() throws Exception {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(null, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(null, "Subj 1", 5, departmentDTO1);

        Mockito.when(subjectService.save(subjectDTO1)).thenReturn(subjectDTO1);

        mockMvc.perform(post("/subject/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectDTO1)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo(subjectDTO1.getName())))
                .andExpect(jsonPath("$.espb", equalTo(subjectDTO1.getEspb())))
                .andExpect(jsonPath("$.departmentDTO.name", equalTo(subjectDTO1.getDepartmentDTO().getName())))
                .andExpect(jsonPath("$.departmentDTO.shortName", equalTo(subjectDTO1.getDepartmentDTO().getShortName())));

        Mockito.verify(subjectService, Mockito.times(1)).save(subjectDTO1);
    }

    @Test
    public void testSaveSubjectFailure() throws Exception {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(null, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(null, "Subj 1", 5, departmentDTO1);

        Mockito.when(subjectService.save(subjectDTO1)).thenThrow(Exception.class);

        mockMvc.perform(get("/subject/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subjectDTO1)))
                .andExpect(status().is(405));
    }
}
