package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.SubjectConverter;
import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.repository.DepartmentRepository;
import com.ivastanisic.nst.repository.SubjectRepository;
import com.ivastanisic.nst.service.abstraction.SubjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private final SubjectRepository subjectRepository;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final SubjectConverter subjectConverter;

    @Override
    public SubjectDTO save(SubjectDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<SubjectDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public SubjectDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public SubjectDTO update(SubjectDTO subjectDTO) throws Exception {
        return null;
    }

    @Override
    public SubjectDTO updateById(Long aLong, SubjectDTO subjectDTO) throws Exception {
        return null;
    }
}
