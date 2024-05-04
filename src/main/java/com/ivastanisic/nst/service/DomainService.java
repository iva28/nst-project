package com.ivastanisic.nst.service;


import com.ivastanisic.nst.dto.MemberDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DomainService <DTO, ID>{
    DTO save(DTO obj) throws Exception;
    List<DTO> getAll();
    void delete(ID id) throws Exception;
    DTO findById(ID id) throws Exception;
    DTO update(DTO dto) throws Exception;
    DTO updateById(ID id, DTO dto) throws Exception;

}
