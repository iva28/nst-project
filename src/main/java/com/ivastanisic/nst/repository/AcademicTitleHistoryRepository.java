package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    List<AcademicTitleHistory> findByMemberId(Long id);
}
