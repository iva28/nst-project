package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.MemberRoleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRoleHistoryRepository extends JpaRepository<MemberRoleHistory, Long> {

    List<MemberRoleHistory> findByDepartmentShortName(String shortName);
}
