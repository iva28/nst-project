INSERT INTO tbl_member(first_name, last_name, start_date,role,department_id, academic_title_id,
                       education_title_id, scientific_field_id)
       VALUES ("Ana",
               "Anic",
               "2022-01-11",
               "NORMAL",
               (SELECT ID FROM tbl_department WHERE name = "Department of Software Engineering"),
               (SELECT ID FROM tbl_academic_title WHERE name = "Lecturer"),
               (SELECT ID FROM tbl_education_title WHERE name = "Graduate with Master's Degree"),
               (SELECT ID FROM tbl_scientific_field WHERE name = "Artificial intelligence")
               );

INSERT INTO tbl_member(first_name, last_name, start_date,role,department_id, academic_title_id,
                      education_title_id, scientific_field_id)
      VALUES ("Nikolina",
              "Nikic",
              "2020-01-08",
              "DIRECTOR",
              (SELECT ID FROM tbl_department WHERE name = "Department of Software Engineering"),
              (SELECT ID FROM tbl_academic_title WHERE name = "Lecturer"),
              (SELECT ID FROM tbl_education_title WHERE name = "Graduate with Master's Degree"),
              (SELECT ID FROM tbl_scientific_field WHERE name = "Artificial intelligence")
              );