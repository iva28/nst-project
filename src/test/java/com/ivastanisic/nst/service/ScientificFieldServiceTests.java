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
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
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

    @Test
    public void testSaveScientificFieldSuccess() throws Exception {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.empty());

        Mockito.when(scientificFieldConverter.toEntity(fieldDTO)).thenReturn(field);
        Mockito.when(scientificFieldRepository.save(field)).thenReturn(field);

        Mockito.when(scientificFieldConverter.toDTO(field)).thenReturn(fieldDTO);

        ScientificFieldDTO savedField = scientificFieldService.save(fieldDTO);
        Assertions.assertNotNull(savedField);
        Assertions.assertEquals(fieldDTO.getName(), savedField.getName());
    }

    @Test
    public void testSaveScientificFieldFailure() throws Exception {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        Mockito.when(scientificFieldConverter.toEntity(fieldDTO)).thenReturn(field);
        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.of(field));
        Assertions.assertThrows(Exception.class, () -> scientificFieldService.save(fieldDTO));
    }

    @Test
    public void testDeleteScientificFieldSuccess() throws Exception {
        Long id = 1l;
        ScientificField field = new ScientificField(id, "Scientific field 1");

        Mockito.when(scientificFieldRepository.findById(id)).thenReturn(Optional.of(field));
        scientificFieldService.delete(id);
        Mockito.verify(scientificFieldRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteScientificFieldFailure() throws Exception {
        Long id = 1l;
        ScientificField field = new ScientificField(id, "Scientific field 1");

        Mockito.when(scientificFieldRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> scientificFieldService.delete(id));
    }

    @Test
    public void testFindScientificFieldByNameSuccess() throws Exception {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.of(field));
        Mockito.when(scientificFieldConverter.toDTO(field)).thenReturn(fieldDTO);
        Mockito.when(scientificFieldConverter.toEntity(fieldDTO)).thenReturn(field);

        ScientificFieldDTO fieldFound = scientificFieldService.findByName(field.getName());
        Assertions.assertNotNull(fieldFound);
        Assertions.assertEquals(field.getName(), fieldFound.getName());
    }

    @Test
    public void testFindScientificFieldByNameFailure() throws Exception {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.empty());
        Mockito.when(scientificFieldConverter.toDTO(field)).thenReturn(fieldDTO);
        Mockito.when(scientificFieldConverter.toEntity(fieldDTO)).thenReturn(field);

        Assertions.assertThrows(Exception.class, () -> scientificFieldService.findByName(field.getName()));
    }

    @Test
    public void testFindScientificFieldByIdNotImplementedYet() throws Exception {
        ScientificFieldDTO byId = scientificFieldService.findById(1L);
        Assertions.assertNull(byId);
    }

    @Test
    public void testUpdateScientificFieldNotImplementedYet() throws Exception {
        ScientificFieldDTO update = scientificFieldService.update(new ScientificFieldDTO());
        Assertions.assertNull(update);
    }

    @Test
    public void testUpdateScientificFieldByIdNotImplementedYet() throws Exception {
        ScientificFieldDTO fieldDTO = scientificFieldService.updateById(1l, new ScientificFieldDTO());
        Assertions.assertNull(fieldDTO);
    }

    @Test
    public void testFindScientificFieldByNameNull() throws Exception {
        Assertions.assertThrows(Exception.class, () -> scientificFieldService.findByName(null));
    }

    @Test
    public void testSaveScientificFieldInvalidInputEmpty() {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(null, "");

        Mockito.when(scientificFieldConverter.toDTO(field)).thenReturn(fieldDTO);
        Mockito.when(scientificFieldConverter.toEntity(fieldDTO)).thenReturn(field);
        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> scientificFieldService.save(fieldDTO));
    }

    @Test
    public void testSaveScientificFieldInvalidInput() {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(null, "string");

        Mockito.when(scientificFieldRepository.findByNameIgnoreCase(field.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> scientificFieldService.save(fieldDTO));
    }
}
