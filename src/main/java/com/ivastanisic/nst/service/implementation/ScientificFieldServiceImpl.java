package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.ScientificFieldConverter;
import com.ivastanisic.nst.domain.ScientificField;
import com.ivastanisic.nst.dto.ScientificFieldDTO;
import com.ivastanisic.nst.repository.ScientificFieldRepository;
import com.ivastanisic.nst.service.abstraction.ScientificFieldService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScientificFieldServiceImpl implements ScientificFieldService {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public ScientificFieldDTO save(ScientificFieldDTO obj) throws Exception {
        Optional<ScientificField> scientificField = scientificFieldRepository.findByName(obj.getName());
        if (scientificField.isPresent())
            throw new Exception("Scientific field with name "+obj.getName()+" doesn't exist");
        ScientificField savedScientificField = scientificFieldRepository.save(scientificFieldConverter.toEntity(obj));
        return scientificFieldConverter.toDTO(savedScientificField);
    }

    @Override
    public List<ScientificFieldDTO> getAll() {
        return scientificFieldConverter.listToDTO(scientificFieldRepository.findAll());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ScientificField> scientificField = scientificFieldRepository.findById(id);
        if (scientificField.isEmpty())
            throw new Exception("Scientific field with id: "+id+" doesn't exist");

        scientificFieldRepository.deleteById(id);
    }

    @Override
    public ScientificFieldDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public ScientificFieldDTO update(ScientificFieldDTO scientificFieldDTO) throws Exception {
        return null;
    }

    @Override
    public ScientificFieldDTO updateById(Long aLong, ScientificFieldDTO scientificFieldDTO) throws Exception {
        return null;
    }
}
