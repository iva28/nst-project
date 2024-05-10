CREATE TABLE tbl_subject(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
    espb INT,
    department_id BIGINT,
	PRIMARY KEY (id),
    CONSTRAINT department_fk FOREIGN KEY (department_id) REFERENCES tbl_department(id)
    ON UPDATE CASCADE ON DELETE CASCADE
);