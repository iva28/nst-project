CREATE TABLE tbl_member(
 id BIGINT NOT NULL AUTO_INCREMENT,
 first_name VARCHAR(30) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 department_id BIGINT,
 academic_title_id BIGINT,
 education_title_id BIGINT,
 scientific_field_id BIGINT,
 PRIMARY KEY(id),
 CONSTRAINT department_fk1 FOREIGN KEY (department_id) REFERENCES tbl_department(id),
 CONSTRAINT academic_title_fk2 FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title(id),
 CONSTRAINT education_title_fk3 FOREIGN KEY (education_title_id) REFERENCES tbl_education_title(id),
 CONSTRAINT scientific_field_fk4 FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field(id),
 ON UPDATE CASCADE ON DELETE RESTRICT
);