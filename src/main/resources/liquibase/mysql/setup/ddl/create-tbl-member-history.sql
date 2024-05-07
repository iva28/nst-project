CREATE TABLE tbl_member_history(
    id BIGINT NOT NULL AUTO_INCREMENT,
    role VARCHAR(30),
    start_date DATE NOT NULL,
    end_date DATE,
    member_id BIGINT,
    department_id BIGINT,
    PRIMARY KEY(id),
    CONSTRAINT member_fk2 FOREIGN KEY (member_id) REFERENCES tbl_member(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT department_fk2 FOREIGN KEY (department_id) REFERENCES tbl_department(id)
    ON UPDATE CASCADE ON DELETE RESTRICT
);