package com.ivastanisic.nst.repository;

import com.ivastanisic.nst.domain.AcademicTitleHistory;
import com.ivastanisic.nst.role.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    List<AcademicTitleHistory> findByMemberId(Long id);
    Optional<AcademicTitleHistory> findByMemberIdAndAcademicTitleIdAndScientificFieldIdAndStartDate(
            Long memberId,
            Long academicTitleId,
            Long scientificFieldId,
            LocalDate startDate
    );
}
