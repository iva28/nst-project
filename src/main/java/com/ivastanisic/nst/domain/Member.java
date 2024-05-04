package com.ivastanisic.nst.domain;

import com.ivastanisic.nst.role.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Member must have first name")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Member must have last name")
    @Column(name= "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @ManyToOne
    @JoinColumn(name = "education_title_id")
    private EducationTitle educationTitle;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<AcademicTitleHistory> academicTitleHistoryList;


}
