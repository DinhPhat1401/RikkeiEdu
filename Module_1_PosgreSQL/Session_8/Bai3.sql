create schema session08;
set search_path to session08;

CREATE TABLE employees (
    emp_id SERIAL PRIMARY KEY,
    emp_name VARCHAR(100),
    job_level INT,
    salary NUMERIC
);

INSERT INTO employees (emp_name, job_level, salary) VALUES
('Nguyễn Văn Một', 1, 10000000),
('Trần Thị Hai', 2, 20000000),
('Lê Văn Ba', 3, 30000000);

CREATE OR REPLACE PROCEDURE adjust_salary(
    p_emp_id INT, 
    OUT p_new_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_level INT;
    v_ratio NUMERIC;
BEGIN
    SELECT job_level INTO v_level FROM employees WHERE emp_id = p_emp_id;

    IF v_level = 1 THEN
        v_ratio := 1.05; 
    ELSIF v_level = 2 THEN
        v_ratio := 1.10; 
    ELSIF v_level = 3 THEN
        v_ratio := 1.15; 
    ELSE
        v_ratio := 1.00; 
    END IF;


    UPDATE employees 
    SET salary = salary * v_ratio
    WHERE emp_id = p_emp_id
    RETURNING salary INTO p_new_salary;

    RAISE NOTICE 'Nhân viên ID % (Level %) đã được cập nhật lương mới.', p_emp_id, v_level;
END;
$$;

DO $$
DECLARE
    p_new_salary NUMERIC;
BEGIN

    CALL adjust_salary(3, p_new_salary);
    
END $$;

SELECT * FROM employees WHERE emp_id = 3;
