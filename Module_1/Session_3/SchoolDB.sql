CREATE DATABASE "SchoolDB";

CREATE TABLE Students (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE
);

CREATE TABLE Courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INTEGER
);

CREATE TABLE Enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    student_id INTEGER,
    course_id INTEGER,
    grade CHAR(1),
    CONSTRAINT fk_enrollments_students 
        FOREIGN KEY (student_id) 
        REFERENCES Students(student_id),
    CONSTRAINT fk_enrollments_courses 
        FOREIGN KEY (course_id) 
        REFERENCES Courses(course_id),
    CONSTRAINT chk_grade_values 
        CHECK (grade IN ('A', 'B', 'C', 'D', 'F'))
);