CREATE DATABASE "ElearningDB";

CREATE SCHEMA elearning;

CREATE TABLE elearning.Students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE elearning.Instructors (
    instructor_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE elearning.Courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    instructor_id INTEGER,
    CONSTRAINT fk_courses_instructors 
        FOREIGN KEY (instructor_id) 
        REFERENCES elearning.Instructors(instructor_id)
);

CREATE TABLE elearning.Enrollments (
    enrollment_id SERIAL PRIMARY KEY,
    student_id INTEGER,
    course_id INTEGER,
    enroll_date DATE NOT NULL,
    CONSTRAINT fk_enrollments_students 
        FOREIGN KEY (student_id) 
        REFERENCES elearning.Students(student_id),
    CONSTRAINT fk_enrollments_courses 
        FOREIGN KEY (course_id) 
        REFERENCES elearning.Courses(course_id)
);


CREATE TABLE elearning.Assignments (
    assignment_id SERIAL PRIMARY KEY,
    course_id INTEGER,
    title VARCHAR(100) NOT NULL,
    due_date DATE NOT NULL,
    CONSTRAINT fk_assignments_courses 
        FOREIGN KEY (course_id) 
        REFERENCES elearning.Courses(course_id)
);


CREATE TABLE elearning.Submissions (
    submission_id SERIAL PRIMARY KEY,
    assignment_id INTEGER,
    student_id INTEGER,
    submission_date DATE NOT NULL,
    grade NUMERIC,
    CONSTRAINT chk_grade_range CHECK (grade >= 0 AND grade <= 100),
    CONSTRAINT fk_submissions_assignments 
        FOREIGN KEY (assignment_id) 
        REFERENCES elearning.Assignments(assignment_id),
    CONSTRAINT fk_submissions_students 
        FOREIGN KEY (student_id) 
        REFERENCES elearning.Students(student_id)
);