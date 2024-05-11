package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Department;
import com.ivastanisic.nst.domain.Member;
import com.ivastanisic.nst.role.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByRole(MemberRole memberRole);
//    @Query("SELECT e FROM Member e WHERE e.department.id = :department")
//    List<Member> findDepartmentId(@Param("department") Long id);
    List<Member> findByDepartmentId(Long id);
    Optional<Member> findByFirstNameAndLastNameAndDepartmentShortName(String firstName, String lastName,String shortName);
    @Query("SELECT e FROM Member e WHERE e.academicTitle.name = :title AND e.role <> 'INACTIVE'")
    List<Member> findByAcademicTitleName(@Param("title") String title);

    Optional<Member> findByRoleAndDepartmentShortName(MemberRole role, String shortName);
    @Query("SELECT e FROM Member e WHERE e.role != 'INACTIVE'")
    List<Member> findAllActive();
    @Query("SELECT e FROM Member e WHERE e.role = 'INACTIVE'")
    List<Member> findAllInactive();
}
