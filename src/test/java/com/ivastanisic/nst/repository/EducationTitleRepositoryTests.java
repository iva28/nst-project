package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.domain.EducationTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class EducationTitleRepositoryTests {
    @Autowired
    EducationTitleRepository educationTitleRepository;

    @Test
    public void testFindByNameIgnoreCase() {
        EducationTitle title = new EducationTitle(1l, "Title");
        EducationTitle saved = educationTitleRepository.save(title);
        Assertions.assertNotNull(saved);

        Optional<EducationTitle> byNameIgnoreCase = educationTitleRepository.findByNameIgnoreCase(title.getName());
        Assertions.assertNotNull(byNameIgnoreCase);
        Assertions.assertEquals(saved.getName(), byNameIgnoreCase.get().getName());
    }
}
