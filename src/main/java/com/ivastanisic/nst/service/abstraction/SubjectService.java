package com.ivastanisic.nst.service.abstraction;

import com.ivastanisic.nst.dto.SubjectDTO;
import com.ivastanisic.nst.service.DomainService;

import java.util.List;

public interface SubjectService extends DomainService<SubjectDTO, Long> {
    List<SubjectDTO> findByDepartmentName(String name) throws Exception;
}
