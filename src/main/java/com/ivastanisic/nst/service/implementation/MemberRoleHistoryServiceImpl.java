package com.ivastanisic.nst.service.implementation;

import com.ivastanisic.nst.dto.MemberRoleHistoryDTO;
import com.ivastanisic.nst.service.abstraction.MemberRoleHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MemberRoleHistoryServiceImpl implements MemberRoleHistoryService {
    @Override
    public MemberRoleHistoryDTO save(MemberRoleHistoryDTO obj) throws Exception {
        return null;
    }

    @Override
    public List<MemberRoleHistoryDTO> getAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public MemberRoleHistoryDTO findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO update(MemberRoleHistoryDTO memberRoleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO updateById(Long aLong, MemberRoleHistoryDTO memberRoleHistoryDTO) throws Exception {
        return null;
    }

    @Override
    public MemberRoleHistoryDTO findByName(String name) throws Exception {
        return null;
    }
}
