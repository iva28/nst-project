package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.AcademicTitleConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.repository.AcademicTitleRepository;
import com.ivastanisic.nst.service.abstraction.AcademicTitleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void testSaveAcademicTitleSuccess() throws Exception {
        AcademicTitle title1 = new AcademicTitle(1l, "Title 1");
        AcademicTitleDTO titleDTO1 = new AcademicTitleDTO(1l, "Title 1");

        Mockito.when(academicTitleRepository.findByNameIgnoreCase(title1.getName())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.save(title1)).thenReturn(title1);
        Mockito.when(academicTitleConverter.toDTO(title1)).thenReturn(titleDTO1);
        Mockito.when(academicTitleConverter.toEntity(titleDTO1)).thenReturn(title1);

        AcademicTitleDTO save = academicTitleService.save(titleDTO1);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(titleDTO1, save);
    }

    @Test
    public void testSaveAcademicTitleFailure() throws Exception {
        AcademicTitle title1 = new AcademicTitle(1l, "Title 1");
        AcademicTitleDTO titleDTO1 = new AcademicTitleDTO(1l, "Title 1");

        Mockito.when(academicTitleRepository.findByNameIgnoreCase(title1.getName())).thenReturn(Optional.of(title1));
        Assertions.assertThrows(Exception.class, () -> academicTitleService.save(titleDTO1));
    }

    @Test
    public void testDeleteAcademicTitleSuccess() throws Exception{
        AcademicTitle title1 = new AcademicTitle(1l, "Title 1");
        AcademicTitleDTO titleDTO1 = new AcademicTitleDTO(1l, "Title 1");

        Mockito.when(academicTitleRepository.findById(title1.getId())).thenReturn(Optional.of(title1));
        academicTitleService.delete(title1.getId());
        Mockito.verify(academicTitleRepository, Mockito.times(1)).deleteById(title1.getId());
    }

    @Test
    public void testDeleteAcademicTitleFailure() throws Exception{
        AcademicTitle title1 = new AcademicTitle(1l, "Title 1");
        Mockito.when(academicTitleRepository.findById(title1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> academicTitleService.delete(title1.getId()));
    }


}
