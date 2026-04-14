
CREATE SCHEMA university;

CREATE TABLE university.Students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE university.Courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INTEGER
);

CREATE TABLE university.Enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    CONSTRAINT fk_enrollments_students 
        FOREIGN KEY (student_id) 
        REFERENCES university.Students(student_id),
    CONSTRAINT fk_enrollments_courses 
        FOREIGN KEY (course_id) 
        REFERENCES university.Courses(course_id),
    enroll_date DATE
);

SELECT datname FROM pg_database;

SELECT schema_name FROM information_schema.schemata;

SELECT table_name, column_name, data_type, character_maximum_length, is_nullable
FROM information_schema.columns
WHERE table_schema = 'university' 
AND table_name = 'students'

SELECT table_name, column_name, data_type, character_maximum_length, is_nullable
FROM information_schema.columns
WHERE table_schema = 'university' 
AND table_name = 'courses'



SELECT table_name, column_name, data_type, character_maximum_length, is_nullable
FROM information_schema.columns
WHERE table_schema = 'university' 
AND table_name = 'enrollments'


