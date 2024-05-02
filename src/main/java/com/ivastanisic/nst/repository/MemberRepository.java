package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.role.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByRole(MemberRole memberRole);
//    @Query("SELECT e FROM Member e WHERE e.department.id = :department")
//    List<Member> findDepartmentId(@Param("department") Long id);
    List<Member> findByDepartmentId(Long id);
}
