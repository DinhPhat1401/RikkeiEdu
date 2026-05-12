set search_path to session06

CREATE TABLE Department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

alter table employee drop column hire_date

alter table employee drop column department

alter table employee add column department_id INT REFERENCES Department(id)
-- 2. CHÈN DỮ LIỆU MẪU
INSERT INTO Department (name) VALUES 
('IT'), 
('HR'), 
('Marketing'), 
('Research'); 

INSERT INTO Employee (full_name, department_id, salary) VALUES
('Nguyễn Văn An', 1, 15000000.00),
('Lê Văn Bình', 1, 25000000.00),  
('Trần Thị Chi', 2, 8000000.00),   
('Phạm Minh Đức', 2, 9000000.00), 
('Hoàng Lan Anh', 3, 12000000.00); 


SELECT e.full_name, d.name AS department_name
FROM Employee e
INNER JOIN Department d ON e.department_id = d.id;

-- Câu 2: Tính lương trung bình của từng phòng ban
SELECT 
    d.name AS department_name, 
    AVG(e.salary) AS avg_salary
FROM Department d
JOIN Employee e ON d.id = e.department_id
GROUP BY d.name;

-- Câu 3: Hiển thị các phòng ban có lương trung bình > 10 triệu (HAVING)
SELECT 
    d.name AS department_name, 
    AVG(e.salary) AS avg_salary
FROM Department d
JOIN Employee e ON d.id = e.department_id
GROUP BY d.name
HAVING AVG(e.salary) > 10000000;

-- Câu 4: Liệt kê phòng ban không có nhân viên nào
SELECT d.name
FROM Department d
LEFT JOIN Employee e ON d.id = e.department_id
WHERE e.id IS NULL;