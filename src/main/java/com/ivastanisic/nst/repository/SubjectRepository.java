package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
