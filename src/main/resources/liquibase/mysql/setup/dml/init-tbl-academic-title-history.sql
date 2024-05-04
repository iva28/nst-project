INSERT INTO tbl_academic_title_history (start_date, end_date, member_id, academic_title_id, scientific_field_id)
            VALUES ("2023-01-10", "2024-01-10",
            (SELECT id FROM tbl_member WHERE first_name = "Ana" AND last_name = "Anic"),
            (SELECT id FROM tbl_academic_title WHERE name = "Assistant Professor"),
            (SELECT id FROM tbl_scientific_field WHERE name = "Web development")
);