package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.AcademicTitleConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.repository.AcademicTitleRepository;
import com.ivastanisic.nst.service.abstraction.AcademicTitleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class AcademicTitleServiceTests {
    @Autowired
    AcademicTitleService academicTitleService;
    @MockBean
    AcademicTitleRepository academicTitleRepository;
    @MockBean
    AcademicTitleConverter academicTitleConverter;

    @Test
    public void testGetAllAcademicTitles() {
        AcademicTitle title1 = new AcademicTitle(1l, "Title 1");
        AcademicTitle title2 = new AcademicTitle(2l, "Title 2");
        List<AcademicTitle> titles = List.of(title1, title2);

        AcademicTitleDTO titleDTO1 = new AcademicTitleDTO(1l, "Title 1");
        AcademicTitleDTO titleDTO2 = new AcademicTitleDTO(2l, "Title 2");
        List<AcademicTitleDTO> titleDTOS = List.of(titleDTO1, titleDTO2);

        Mockito.when(academicTitleRepository.findAll()).thenReturn(titles);
        Mockito.when(academicTitleConverter.listToDTO(titles)).thenReturn(titleDTOS);

        List<AcademicTitleDTO> all = academicTitleService.getAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(titleDTOS, all);
    }



}
