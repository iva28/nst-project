package com.ivastanisic.nst.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @ManyToOne
    @JoinColumn(name = "education_title_id")
    private EducationTitle educationTitle;
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;


}
