package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.ScientificFieldConverter;
import com.ivastanisic.nst.domain.ScientificField;
import com.ivastanisic.nst.dto.ScientificFieldDTO;
import com.ivastanisic.nst.repository.ScientificFieldRepository;
import com.ivastanisic.nst.service.abstraction.ScientificFieldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ScientificFieldServiceTests {
    @Autowired
    private ScientificFieldService scientificFieldService;
    @MockBean
    private ScientificFieldConverter scientificFieldConverter;
    @MockBean
    private ScientificFieldRepository scientificFieldRepository;

    @Test
    public void getAllScientificFields() {
        ScientificField field1 = new ScientificField(1l, "Scientific field 1");
        ScientificField field2 = new ScientificField(2l, "Scientific field 2");
        List<ScientificField> fields = List.of(field1, field2);

        ScientificFieldDTO fieldDTO1 = new ScientificFieldDTO(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO2 = new ScientificFieldDTO(2l, "Scientific field 2");
        List<ScientificFieldDTO> fieldsDTO = List.of(fieldDTO1, fieldDTO2);

        Mockito.when(scientificFieldRepository.findAll()).thenReturn(fields);
        Mockito.when(scientificFieldConverter.listToDTO(fields)).thenReturn(fieldsDTO);
        Mockito.when(scientificFieldConverter.toDTO(field1)).thenReturn(fieldDTO1);
        Mockito.when(scientificFieldConverter.toDTO(field2)).thenReturn(fieldDTO2);

        List<ScientificFieldDTO> scientificFields = scientificFieldService.getAll();
        Assertions.assertNotNull(scientificFields);
        Assertions.assertEquals(scientificFields.get(0), fieldDTO1);
        Assertions.assertEquals(scientificFields.get(1), fieldDTO2);
    }
}
