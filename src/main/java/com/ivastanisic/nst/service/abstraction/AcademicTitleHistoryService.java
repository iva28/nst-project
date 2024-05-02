package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface AcademicTitleHistoryService extends DomainService<AcademicTitleHistoryDTO, Long> {
    public List<AcademicTitleHistoryDTO> getAllByMemberId(Long id);
}
