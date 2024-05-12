package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByDepartmentShortName(String name);
    Optional<Subject> findByName(String name);
}
