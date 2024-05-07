package com.ivastanisic.nst.domain;

import com.ivastanisic.nst.role.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_member_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    @Column(name = "start_date")
    @NotNull
    private LocalDate startDate;
    @Column(name = "end_date")
    @NotNull
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


}
