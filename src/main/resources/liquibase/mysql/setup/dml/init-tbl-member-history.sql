INSERT INTO tbl_member_role_history(role, start_date, end_date,
                                member_id, department_id)
VALUES(
        "DIRECTOR",
        "2010-01-07",
         "2011-01-07",
         (SELECT(id) FROM tbl_member WHERE first_name = "Ana" AND last_name = "Anic"),
         (SELECT(id) FROM tbl_department WHERE short_name = "SILAB")
         );

