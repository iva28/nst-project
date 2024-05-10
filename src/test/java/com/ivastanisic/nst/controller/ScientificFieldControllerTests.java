package com.ivastanisic.nst.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivastanisic.nst.dto.ScientificFieldDTO;
import com.ivastanisic.nst.service.abstraction.ScientificFieldService;
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

@WebMvcTest(ScientificFieldController.class)
public class ScientificFieldControllerTests {

    @MockBean
    private ScientificFieldService scientificFieldService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllScientificFields() throws Exception {
        ScientificFieldDTO field1 = new ScientificFieldDTO(1l, "Scientific field 1");
        ScientificFieldDTO field2 = new ScientificFieldDTO(2l, "Scientific field 2");

        List<ScientificFieldDTO> fields = List.of(field1, field2);
        Mockito.when(scientificFieldService.getAll()).thenReturn(fields);

        MvcResult result = mockMvc.perform(get("/scientific-field"))
                .andExpect(status().isOk()).andReturn();

        List<ScientificFieldDTO> resultFields = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<ScientificFieldDTO>>() {

                });

        Assertions.assertNotNull(resultFields);
        Assertions.assertEquals(field1.getName(), resultFields.get(0).getName());
        Assertions.assertEquals(field2.getName(), resultFields.get(1).getName());
        Mockito.verify(scientificFieldService, Mockito.times(1)).getAll();
    }

    @Test
    public void testSaveScientificFieldSuccess() throws Exception {
        ScientificFieldDTO field = new ScientificFieldDTO(null, "Scientific field 1");
        Mockito.when(scientificFieldService.save(field)).thenReturn(field);

        mockMvc.perform(post("/scientific-field")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(field)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo(field.getName())));

        Mockito.verify(scientificFieldService, Mockito.times(1)).save(field);
    }

    @Test
    public void testSaveScientificFieldFailure() throws Exception {
        ScientificFieldDTO field = new ScientificFieldDTO(null, "Scientific field 1");
        Mockito.when(scientificFieldService.save(field)).thenThrow(Exception.class);

        mockMvc.perform(post("/scientific-field")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(field)))
                .andExpect(status().isBadRequest());

        Mockito.verify(scientificFieldService, Mockito.times(1)).save(field);
    }

    @Test
    public void testDeleteScientificFieldSuccess() throws Exception {
        Long id = 1l;
        Mockito.doNothing().when(scientificFieldService).delete(id);

        mockMvc.perform(delete("/scientific-field/{id}", id))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

        Mockito.verify(scientificFieldService, Mockito.times(1)).delete(id);
    }

    @Test
    public void testDeleteScientificFieldFailure() throws Exception {
        Long id = 1l;
        Mockito.doThrow(Exception.class).when(scientificFieldService).delete(id);

        mockMvc.perform(delete("/scientific-field/{id}",id))
                .andExpect(status().isBadRequest());

        Mockito.verify(scientificFieldService, Mockito.times(1)).delete(id);
    }
}
