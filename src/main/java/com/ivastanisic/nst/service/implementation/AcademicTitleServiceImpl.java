package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.AcademicTitleConverter;
import com.ivastanisic.nst.domain.AcademicTitle;
import com.ivastanisic.nst.dto.AcademicTitleDTO;
import com.ivastanisic.nst.repository.AcademicTitleRepository;
import com.ivastanisic.nst.service.abstraction.AcademicTitleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AcademicTitleServiceImpl implements AcademicTitleService {
    @Autowired
    private final AcademicTitleRepository academicTitleRepository;
    @Autowired
    private final AcademicTitleConverter academicTitleConverter;

    @Override
    public AcademicTitleDTO save(AcademicTitleDTO obj) throws Exception {
        System.out.println("Saving academic title");
        System.out.println(obj);

        Optional<AcademicTitle> existingAcademicTitle = academicTitleRepository.findByName(obj.getName());
        if (existingAcademicTitle.isPresent())
            throw new Exception("Academic title with this name already exist");

        AcademicTitle academicTitle = academicTitleConverter.toEntity(obj);
        return academicTitleConverter.toDTO(academicTitleRepository.save(academicTitle));
    }

    @Override
    public List<AcademicTitleDTO> getAll() {
        return academicTitleConverter.listToDTO(academicTitleRepository.findAll());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AcademicTitle> academicTitle = academicTitleRepository.findById(id);
        if (!academicTitle.isPresent())
            throw new Exception("Academic title with id " + id + " doesn't exits");

        academicTitleRepository.deleteById(id);
    }

    @Override
    public AcademicTitleDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleDTO update(AcademicTitleDTO academicTitleDTO) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleDTO updateById(Long aLong, AcademicTitleDTO academicTitleDTO) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleDTO findByName(String name) throws Exception {
        if (name == null)
            throw new Exception("Name of academic title can't be empty");

        return academicTitleConverter.toDTO(academicTitleRepository.findByName(name).get());
    }
}
