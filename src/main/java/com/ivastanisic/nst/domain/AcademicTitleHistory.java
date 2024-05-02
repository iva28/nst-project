package com.ivastanisic.nst.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivastanisic.nst.role.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Academic title must have start date")
    @Column(name = "start_date")
    private LocalDate startDate;
    @NotEmpty(message = "Academic title must have end date")
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;

}
