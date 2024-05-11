package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.MemberRoleHistory;
import com.ivastanisic.nst.role.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRoleHistoryRepository extends JpaRepository<MemberRoleHistory, Long> {

    List<MemberRoleHistory> findByDepartmentShortName(String shortName);

    List<MemberRoleHistory> findByRole(MemberRole role);
    @Query("SELECT e FROM MemberRoleHistory e WHERE e.department.shortName = :name AND e.role IN ('DIRECTOR','SECRETARY')")
    List<MemberRoleHistory> findHistoryForDepartment(@Param("name") String name);
}
