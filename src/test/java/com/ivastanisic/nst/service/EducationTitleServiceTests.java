package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.EducationTitleConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.domain.EducationTitle;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.dto.EducationTitleDTO;
import com.ivastanisic.nst.repository.EducationTitleRepository;
import com.ivastanisic.nst.service.abstraction.EducationTitleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class EducationTitleServiceTests {
    @Autowired
    EducationTitleService educationTitleService;
    @MockBean
    EducationTitleRepository educationTitleRepository;
    @MockBean
    EducationTitleConverter educationTitleConverter;

    @Test
    public void testSaveEducationTitleFailure() throws Exception {
        EducationTitle title = new EducationTitle(1l, "Title");
        EducationTitleDTO titleDTO = new EducationTitleDTO(1l, "Title");

        Mockito.when(educationTitleRepository.findByNameIgnoreCase(title.getName())).thenReturn(Optional.of(title));
        Assertions.assertThrows(Exception.class, () -> educationTitleService.save(titleDTO));
    }

    @Test
    public void testSaveEducationTitleSuccess() throws Exception {
        EducationTitle title = new EducationTitle(1l, "Title");
        EducationTitleDTO titleDTO = new EducationTitleDTO(1l, "Title");

        Mockito.when(educationTitleRepository.findByNameIgnoreCase(title.getName())).thenReturn(Optional.empty());
        Mockito.when(educationTitleConverter.toDTO(title)).thenReturn(titleDTO);
        Mockito.when(educationTitleConverter.toEntity(titleDTO)).thenReturn(title);
        Mockito.when(educationTitleRepository.save(title)).thenReturn(title);

        EducationTitleDTO save = educationTitleService.save(titleDTO);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(titleDTO, save);
    }

    @Test
    public void testSaveAcademicTitleInvalidInputNull() {
        EducationTitle title1 = new EducationTitle(1l, "Title");
        EducationTitleDTO titleDTO1 = new EducationTitleDTO(1l, null);

        Mockito.when(educationTitleConverter.toDTO(title1)).thenReturn(titleDTO1);
        Mockito.when(educationTitleConverter.toEntity(titleDTO1)).thenReturn(title1);
        Mockito.when(educationTitleRepository.findByNameIgnoreCase(title1.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> educationTitleService.save(titleDTO1));
    }

    @Test
    public void testSaveAcademicTitleInvalidInputEmpty() {
        EducationTitle title1 = new EducationTitle(1l, "Title");
        EducationTitleDTO titleDTO1 = new EducationTitleDTO(1l, "");

        Mockito.when(educationTitleConverter.toDTO(title1)).thenReturn(titleDTO1);
        Mockito.when(educationTitleConverter.toEntity(titleDTO1)).thenReturn(title1);
        Mockito.when(educationTitleRepository.findByNameIgnoreCase(title1.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> educationTitleService.save(titleDTO1));
    }

    @Test
    public void testSaveAcademicTitleInvalidInput() {
        EducationTitle title1 = new EducationTitle(1l, "Title");
        EducationTitleDTO titleDTO1 = new EducationTitleDTO(1l, "string");

        Mockito.when(educationTitleRepository.findByNameIgnoreCase(title1.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> educationTitleService.save(titleDTO1));
    }



}
