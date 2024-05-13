package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.AcademicTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class AcademicTitleRepositoryTests {
    @Autowired
    AcademicTitleRepository academicTitleRepository;

    @Test
    public void testFindByNameIgnoreCase() {
        AcademicTitle title = new AcademicTitle(1l, "Title");
        AcademicTitle saved = academicTitleRepository.save(title);
        Assertions.assertNotNull(saved);

        Optional<AcademicTitle> byNameIgnoreCase = academicTitleRepository.findByNameIgnoreCase(title.getName());
        Assertions.assertNotNull(byNameIgnoreCase);
        Assertions.assertEquals(title.getName(), byNameIgnoreCase.get().getName());
    }
}
