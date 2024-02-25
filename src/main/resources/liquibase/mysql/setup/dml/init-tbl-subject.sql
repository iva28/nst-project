INSERT INTO tbl_subject(name,espb,department_id)
VALUES ("Advance programming", 5, ( SELECT (id)
                                    FROM tbl_department
                                    WHERE short_name="SILAB"));

INSERT INTO tbl_subject(name,espb,department_id)
VALUES ("Mathematics 1", 6, ( SELECT (id)
                                    FROM tbl_department
                                    WHERE short_name="MATH"));