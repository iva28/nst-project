package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.EducationTitleConverter;
import com.ivastanisic.nst.domain.EducationTitle;
import com.ivastanisic.nst.dto.EducationTitleDTO;
import com.ivastanisic.nst.repository.EducationTitleRepository;
import com.ivastanisic.nst.service.abstraction.EducationTitleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EducationTitleServiceImpl implements EducationTitleService {
    @Autowired
    private final EducationTitleRepository educationTitleRepository;
    @Autowired
    private final EducationTitleConverter educationTitleConverter;
    @Override
    public EducationTitleDTO save(EducationTitleDTO obj) throws Exception {
        Optional<EducationTitle> educationTitle = educationTitleRepository.findByName(obj.getName());
        if (educationTitle.isPresent())
            throw new Exception("Education title with name: "+obj.getName()+" already exists");

        EducationTitle savedEducationTitle = educationTitleRepository.save(educationTitleConverter.toEntity(obj));
        return educationTitleConverter.toDTO(savedEducationTitle);
    }

    @Override
    public List<EducationTitleDTO> getAll() {
        return educationTitleConverter.listToDTO(educationTitleRepository.findAll());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<EducationTitle> educationTitle = educationTitleRepository.findById(id);
        if (!educationTitle.isPresent())
            throw new Exception("There isn't education title with id: "+id);

        educationTitleRepository.deleteById(id);
    }

    @Override
    public EducationTitleDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public EducationTitleDTO update(EducationTitleDTO educationTitleDTO) throws Exception {
        return null;
    }

    @Override
    public EducationTitleDTO updateById(Long aLong, EducationTitleDTO educationTitleDTO) throws Exception {
        return null;
    }
}