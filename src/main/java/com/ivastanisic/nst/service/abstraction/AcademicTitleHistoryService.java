package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.AcademicTitleHistoryDTO;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface AcademicTitleHistoryService extends DomainService<AcademicTitleHistoryDTO, Long> {
    List<AcademicTitleHistoryDTO> getAllByMemberId(Long id);
}
