package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByNameIgnoreCase(String name);

    Optional<Department> findById(Long id);

    Optional<Department> findByShortNameIgnoreCase(String shortName);

}
