package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByDepartmentShortName(String name);
}
