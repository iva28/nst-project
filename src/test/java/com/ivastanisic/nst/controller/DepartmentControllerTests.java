package com.ivastanisic.nst.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.service.abstraction.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTests {
    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testGetAllDepartments() throws Exception {
        List<DepartmentDTO> departments = List.of(
                new DepartmentDTO(null, "Department 1", "D1"),
                new DepartmentDTO(null, "Department 2", "D2")
        );

        Mockito.when(departmentService.getAll()).thenReturn(departments);

        MvcResult result = mockMvc.perform(get("/department/all"))
                .andExpect(status().isOk()).andReturn();

        List<DepartmentDTO> returnedDepartments = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<DepartmentDTO>>() {
                });

        Assertions.assertNotNull(returnedDepartments);
        Assertions.assertTrue(returnedDepartments.contains(departments.get(0)));
        Assertions.assertTrue(returnedDepartments.contains(departments.get(1)));
        Mockito.verify(departmentService, Mockito.times(1)).getAll();
    }

    @Test
    public void testSaveDepartmentSuccess() throws Exception {
        DepartmentDTO department = new DepartmentDTO(null, "Department 1", "D1");
        Mockito.when(departmentService.save(department)).thenReturn(department);

        mockMvc.perform(post("/department/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo(department.getName())))
                .andExpect(jsonPath("$.shortName", equalTo(department.getShortName())));

        Mockito.verify(departmentService, Mockito.times(1)).save(department);
    }
    @Test
    public void testSaveDepartmentFailure() throws Exception {
        DepartmentDTO department = new DepartmentDTO(null, "Department 1", "D1");
        Mockito.when(departmentService.save(department)).thenThrow(Exception.class);

        mockMvc.perform(post("/department/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department))).
                andExpect(status().isBadRequest());
    }
    @Test
    public void testDeleteDepartmentFailure() throws Exception {
        Long id = 1L;
        Mockito.doThrow(Exception.class).when(departmentService).delete(id);
        mockMvc.perform(delete("/department/{id}", id)).andExpect(status().isBadRequest());
        Mockito.verify(departmentService, Mockito.times(1)).delete(id);
    }
    @Test
    public void testDeleteDepartmentSuccess() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(departmentService).delete(id);
        mockMvc.perform(delete("/department/{id}",id))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

        Mockito.verify(departmentService, Mockito.times(1)).delete(id);
    }
}
