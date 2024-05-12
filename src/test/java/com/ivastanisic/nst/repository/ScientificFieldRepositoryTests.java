package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.ScientificField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ScientificFieldRepositoryTests {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;

    @Test
    public void testFindByName() {
        ScientificField field = new ScientificField(1l, "Scientific field1");
        ScientificField savedField = scientificFieldRepository.save(field);
        Assertions.assertNotNull(savedField);

        Optional<ScientificField> foundField = scientificFieldRepository.findByNameIgnoreCase(field.getName());
        Assertions.assertNotNull(foundField);
        Assertions.assertEquals(field.getName(), foundField.get().getName());
    }

}
