CREATE DATABASE "CompanyDB";

CREATE TABLE Departments (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

CREATE TABLE Employees (
    emp_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE,
    department_id INTEGER,
    CONSTRAINT fk_employees_departments 
        FOREIGN KEY (department_id) 
        REFERENCES Departments(department_id)
);

CREATE TABLE Projects (
    project_id SERIAL PRIMARY KEY,
    project_name VARCHAR(150) NOT NULL,
    start_date DATE,
    end_date DATE
);

CREATE TABLE EmployeeProjects (
    emp_id INTEGER,
    project_id INTEGER,
    PRIMARY KEY (emp_id, project_id),
    CONSTRAINT fk_ep_employees 
        FOREIGN KEY (emp_id) 
        REFERENCES Employees(emp_id),
    CONSTRAINT fk_ep_projects 
        FOREIGN KEY (project_id) 
        REFERENCES Projects(project_id)
);