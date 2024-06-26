package com.ivastanisic.nst.service;

import com.ivastanisic.nst.converter.impl.DepartmentConverter;
import com.ivastanisic.nst.converter.impl.SubjectConverter;
import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Subject;
import com.ivastanisic.nst.dto.DepartmentDTO;
import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.SubjectRepository;
import com.ivastanisic.nst.service.abstraction.SubjectService;
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
public class SubjectServiceTests {
    @Autowired
    private SubjectService subjectService;
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private SubjectConverter subjectConverter;
    @MockBean
    private DepartmentConverter departmentConverter;

    @Test
    public void testGetAll() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);

        DepartmentDTO departmentDTO2 = new DepartmentDTO(2l, "Dep 2", "D2");
        SubjectDTO subjectDTO2 = new SubjectDTO(2l, "Subj 2", 5, departmentDTO2);

        List<SubjectDTO> subjectsDtos = List.of(subjectDTO1, subjectDTO2);

        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);

        Department department2 = new Department(2l, "Dep 2", "D2");
        Subject subject2 = new Subject(2l, "Subj 2", 5, department2);
        List<Subject> subjects = List.of(subject1, subject2);

        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);
        Mockito.when(subjectConverter.listToDTO(subjects)).thenReturn(subjectsDtos);

        List<SubjectDTO> all = subjectService.getAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all, subjectsDtos);
    }

    @Test
    public void testDeleteSubjectSuccess() throws Exception {
        Long id = 1l;
        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);

        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);

        Mockito.when(subjectRepository.findById(id)).thenReturn(Optional.of(subject1));
        Mockito.when(subjectConverter.toDTO(subject1)).thenReturn(subjectDTO1);
        subjectService.delete(id);
        Mockito.verify(subjectRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteSubjectFailure() {
        Long id = 1L;
        Mockito.when(subjectRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> subjectService.delete(id));
    }

    @Test
    public void testFindSubjectByIdSuccess() throws Exception {
        Long id = 1l;
        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);

        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);

        Mockito.when(subjectRepository.findById(id)).thenReturn(Optional.of(subject1));
        Mockito.when(subjectConverter.toDTO(subject1)).thenReturn(subjectDTO1);

        SubjectDTO subjectById = subjectService.findById(id);
        Assertions.assertNotNull(subjectById);
        Assertions.assertEquals(subjectDTO1, subjectById);
    }

    @Test
    public void testFindSubjectByIdFailure() {
        Long id = 1l;
        Mockito.when(subjectRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> subjectService.findById(id));
    }

    @Test
    public void testSaveSubjectExistsFailure() {
        Long id = 1l;
        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);

        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);

        Mockito.when(subjectRepository.findById(id)).thenReturn(Optional.of(subject1));
        Assertions.assertThrows(Exception.class, () -> subjectService.save(subjectDTO1));
    }

    @Test
    public void testSubjectNoDepartmentFailure() {
        Long id = 1l;

        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, null);

        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, null);

        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> subjectService.save(subjectDTO1));
    }

    @Test
    public void testSaveSubjectSuccess() throws Exception {
        Long id = 1l;
        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 6, department1);

        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 6, departmentDTO1);

        Mockito.when(departmentRepository.findByShortNameIgnoreCase(department1.getShortName().toUpperCase()))
                .thenReturn(Optional.of(department1));
        Mockito.when(subjectRepository.findById(subject1.getId())).thenReturn(Optional.empty());
        Mockito.when(subjectConverter.toEntity(subjectDTO1)).thenReturn(subject1);
        Mockito.when(subjectConverter.toDTO(subject1)).thenReturn(subjectDTO1);
        Mockito.when(departmentConverter.toEntity(departmentDTO1)).thenReturn(department1);
        Mockito.when(departmentConverter.toDTO(department1)).thenReturn(departmentDTO1);
        Mockito.when(subjectRepository.save(subject1)).thenReturn(subject1);

        SubjectDTO save = subjectService.save(subjectDTO1);
        Assertions.assertNotNull(save);
        Assertions.assertEquals(subjectDTO1, save);
    }

    @Test
    public void testGetSubjectsByDepartementSuccess() throws Exception {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);
        SubjectDTO subjectDTO2 = new SubjectDTO(2l, "Subj 2", 5, departmentDTO1);

        List<SubjectDTO> subjectsDtos = List.of(subjectDTO1, subjectDTO2);

        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 5, department1);
        Subject subject2 = new Subject(2l, "Subj 2", 5, department1);
        List<Subject> subjects = List.of(subject1, subject2);

        String name = "D1";
        Mockito.when(departmentRepository.findByShortNameIgnoreCase(name)).thenReturn(Optional.of(department1));
        Mockito.when(subjectRepository.findByDepartmentShortName(name)).thenReturn(subjects);
        Mockito.when(subjectConverter.listToDTO(subjects)).thenReturn(subjectsDtos);

        List<SubjectDTO> byDepartmentName = subjectService.findByDepartmentName(name);
        Assertions.assertNotNull(byDepartmentName);

        for (int i = 0; i < byDepartmentName.size(); i++) {
            Assertions.assertEquals(byDepartmentName.get(i).getDepartmentDTO().getShortName(), name);
        }
    }

    @Test
    public void testGetSubjectsByDepartmentFailure() {
        String name = "D1";
        Mockito.when(departmentRepository.findByShortNameIgnoreCase(name)).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> subjectService.findByDepartmentName(name));
    }

    @Test
    public void testUpdateSubjectSuccess() throws Exception {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 8, departmentDTO1);

        Department department1 = new Department(1l, "Dep 1", "D1");
        Subject subject1 = new Subject(1l, "Subj 1", 8, department1);


        Mockito.when(subjectRepository.findById(subjectDTO1.getId()))
                .thenReturn(Optional.of(subject1));
        Mockito.when(subjectConverter.toDTO(subject1)).thenReturn(subjectDTO1);
        Mockito.when(subjectRepository.save(subject1)).thenReturn(subject1);

        SubjectDTO updated = subjectService.update(subjectDTO1);
        Assertions.assertNotNull(updated);
        Assertions.assertEquals(subjectDTO1, updated);
    }

    @Test
    public void testUpdateSubjectFailure() {
        Subject subject1 = new Subject(1l, "Subj 1", 5, null);

        Mockito.when(subjectRepository.findById(subject1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> subjectService.update(new SubjectDTO()));
    }

    @Test
    public void testFindSubjectByNameNotImplementedYet() throws Exception {
        SubjectDTO byName = subjectService.findByName(null);
        Assertions.assertNull(byName);
    }

    @Test
    public void testUpdateSubjectByIdNotImplementedYet() throws Exception {
        SubjectDTO subjectDTO = subjectService.updateById(1l, new SubjectDTO());
        Assertions.assertNull(subjectDTO);
    }

    @Test
    public void testUpdateSubjectEmpty() {
        Assertions.assertThrows(Exception.class, () -> subjectService.update(null));
    }

    @Test
    public void testUpdateSubjectBadEspb() {
        SubjectDTO subjectDTO2 = new SubjectDTO(2l, "Subj 2", 0, null);
        Assertions.assertThrows(Exception.class, () -> subjectService.update(subjectDTO2));
    }

    @Test
    public void testSaveSubjectEmpty() {
        Assertions.assertThrows(Exception.class, () -> subjectService.save(null));
    }

    @Test
    public void testSaveSubjectDepartmentEmpty() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(null, "Dep 1", "D1");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);
        Assertions.assertThrows(Exception.class, () -> subjectService.save(subjectDTO1));
    }

//    @Test
//    public void testSaveSubjectNonExistingDepartment() throws Exception {
//        DepartmentDTO departmentDTO1 = new DepartmentDTO(1l, "Dep 1", "D1");
//        Department department = new Department(1l, "Dep 1", "D1");
//
//        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1",7, departmentDTO1);
//        Subject subject = new Subject(1l, "Subj 1", 7, department);
//
//        Mockito.when(departmentRepository.findByShortNameIgnoreCase(departmentDTO1.getShortName().toUpperCase()))
//                .thenReturn(Optional.empty());
//        Mockito.when(departmentRepository.save(department)).thenReturn(department);
//        Mockito.when(departmentConverter.toDTO(department)).thenReturn(departmentDTO1);
//        Mockito.when(subjectRepository.save(subject)).thenReturn(subject);
//        Mockito.when(subjectConverter.toDTO(subject)).thenReturn(subjectDTO1);
//        Mockito.when(subjectConverter.toEntity(subjectDTO1)).thenReturn(subject);
//
//        SubjectDTO save = subjectService.save(subjectDTO1);
//        Assertions.assertNotNull(save);
//        Assertions.assertEquals(subjectDTO1, save);
//    }

    @Test
    public void testSaveSubjecAlreadyExists() {
        Subject subject = new Subject(1l, "Subj 1", 5, null);
        SubjectDTO subjectDTO = new SubjectDTO();
        Mockito.when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.of(subject));
        Assertions.assertThrows(Exception.class, () -> subjectService.save(subjectDTO));
    }

    @Test
    public void testSaveSubjecNoDepartment() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(null, "Dep 1", "string");
        SubjectDTO subjectDTO1 = new SubjectDTO(1l, "Subj 1", 5, departmentDTO1);
        Assertions.assertThrows(Exception.class, () -> subjectService.save(subjectDTO1));
    }

}
