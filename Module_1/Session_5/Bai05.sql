create schema session05
set search_path to session05


CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    major VARCHAR(50)
);


CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100),
    credit INT
);

CREATE TABLE enrollments (
    student_id INT REFERENCES students(student_id),
    course_id INT REFERENCES courses(course_id),
    score NUMERIC(5,2),
    PRIMARY KEY (student_id, course_id)
);


INSERT INTO students (full_name, major) VALUES 
('Nguyễn Văn A', 'Công nghệ thông tin'),
('Trần Thị B', 'Kinh tế'),
('Lê Văn C', 'Công nghệ thông tin'),
('Phạm Thị D', 'Ngôn ngữ Anh');


INSERT INTO courses (course_name, credit) VALUES 
('Cơ sở dữ liệu', 3),
('Lập trình Java', 4),
('Kinh tế vĩ mô', 3);


INSERT INTO enrollments (student_id, course_id, score) VALUES 
(1, 1, 8.5),
(1, 2, 9.0),
(2, 3, 7.0),
(3, 1, 6.5),
(3, 2, 8.0),
(4, 3, 7.8);

SELECT 
    s.full_name AS "Tên sinh viên", 
    c.course_name AS "Môn học", 
    e.score AS "Điểm"
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
JOIN courses c ON e.course_id = c.course_id;
-------------------
SELECT 
    s.full_name, 
    AVG(e.score) AS diem_trung_binh, 
    MAX(e.score) AS diem_cao_nhat, 
    MIN(e.score) AS diem_thap_nhat
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
GROUP BY s.student_id, s.full_name;
---------------------
SELECT 
    s.major, 
    AVG(e.score) AS avg_major_score
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
GROUP BY s.major
HAVING AVG(e.score) > 7.5;
--------------------
SELECT 
    s.full_name, 
    c.course_name, 
    c.credit, 
    e.score
FROM students s
INNER JOIN enrollments e ON s.student_id = e.student_id
INNER JOIN courses c ON e.course_id = c.course_id;
--------------------
SELECT 
    s.full_name, 
    AVG(e.score) AS personal_avg
FROM students s
JOIN enrollments e ON s.student_id = e.student_id
GROUP BY s.student_id, s.full_name
HAVING AVG(e.score) > (
    SELECT AVG(score) FROM enrollments
);