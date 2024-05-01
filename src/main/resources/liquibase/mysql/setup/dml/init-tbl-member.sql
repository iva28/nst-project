INSERT INTO tbl_member(first_name, last_name, department_id, academic_title_id,
                       education_title_id, scientific_field_id)
       VALUES ("Ana", "Anic",
               (SELECT ID FROM tbl_department WHERE name = "Department of Software Engineering"),
               (SELECT ID FROM tbl_academic_title WHERE name = "Lecturer"),
               (SELECT ID FROM tbl_education_title WHERE name = "Graduate with Master's Degree"),
               (SELECT ID FROM tbl_scientific_field WHERE name = "Artificial intelligence")
               );