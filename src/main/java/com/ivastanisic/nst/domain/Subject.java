package com.ivastanisic.nst.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Subject's name cann't be empty!")
    @Column(name="name")
    private String name;

    @NotNull(message = "Subject must contain espb points")
    @Column(name="espb")

    private int espb;
//    @NotEmpty(message = "Subject must belong to department")
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

}
