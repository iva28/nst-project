package com.ivastanisic.nst.converter;

import com.ivastanisic.nst.converter.impl.ScientificFieldConverter;
import com.ivastanisic.nst.domain.ScientificField;
import com.ivastanisic.nst.dto.ScientificFieldDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ScientificFieldConverterTests {

    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Test
    public void toEntityTest() {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        ScientificField entity = scientificFieldConverter.toEntity(fieldDTO);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(field, entity);
    }

    @Test
    public void toDtoTest() {
        ScientificField field = new ScientificField(1l, "Scientific field 1");
        ScientificFieldDTO fieldDTO = new ScientificFieldDTO(1l, "Scientific field 1");

        ScientificFieldDTO dto = scientificFieldConverter.toDTO(field);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(fieldDTO, dto);
    }

    @Test
    public void toEntityTestFail() {
        ScientificField entity = scientificFieldConverter.toEntity(null);
        Assertions.assertNull(entity);
    }

    @Test
    public void toDtoTestFail() {
        ScientificFieldDTO dto = scientificFieldConverter.toDTO(null);
        Assertions.assertNull(dto);
    }

}
