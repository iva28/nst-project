package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.converter.impl.AcademicTitleHistoryConverter;
import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.repository.AcademicTitleHistoryRepository;
import com.ivastanisic.nst.service.abstraction.AcademicTitleHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    @Autowired
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Autowired
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    @Override
    public AcademicTitleHistoryDTO save(AcademicTitleHistoryDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<AcademicTitleHistoryDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public AcademicTitleHistoryDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleHistoryDTO update(AcademicTitleHistoryDTO academicTitleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public AcademicTitleHistoryDTO updateById(Long aLong, AcademicTitleHistoryDTO academicTitleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public List<AcademicTitleHistoryDTO> getAllByMemberId(Long id) {
        return academicTitleHistoryConverter.listToDTO(academicTitleHistoryRepository.findByMemberId(id));
    }
}
