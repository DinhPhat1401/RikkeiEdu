create schema session06
set search_path to session06

CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    department VARCHAR(50),
    salary NUMERIC(10,2),
    hire_date DATE
);

INSERT INTO Employee (full_name, department, salary, hire_date) VALUES
('Nguyễn Văn An', 'IT', 15000000.00, '2023-05-15'),
('Trần Thị Bình', 'HR', 8000000.00, '2022-10-20'),
('Lê Văn An', 'IT', 12000000.00, '2023-01-10'),
('Phạm Minh Tú', 'Marketing', 5500000.00, '2024-02-01'),
('Hoàng Thanh an', 'IT', 20000000.00, '2023-12-25'),
('Đặng Ngọc Lan', 'Sales', 9000000.00, '2023-07-14');


UPDATE Employee 
SET salary = salary * 1.1 
WHERE department = 'IT';


DELETE FROM Employee 
WHERE salary < 6000000;


SELECT * FROM Employee 
WHERE full_name LIKE '%An';


SELECT * FROM Employee 
WHERE hire_date BETWEEN '2023-01-01' AND '2023-12-31';