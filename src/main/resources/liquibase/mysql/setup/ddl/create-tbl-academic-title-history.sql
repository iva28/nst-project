CREATE TABLE tbl_academic_title_history(
    id BIGINT NOT NULL AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE,
    member_id BIGINT,
    academic_title_id BIGINT,
    scientific_field_id BIGINT,
    PRIMARY KEY(id),
    CONSTRAINT member_fk1 FOREIGN KEY (member_id) REFERENCES tbl_member(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT academic_title_fk2_2 FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT scientific_field_fk3 FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);