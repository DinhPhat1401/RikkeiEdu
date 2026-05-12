create schema session06;
set search_path to session06

CREATE TABLE Course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    instructor VARCHAR(50),
    price NUMERIC(10,2),
    duration INT -- số giờ học
);

INSERT INTO Course (title, instructor, price, duration) VALUES
('Lập trình SQL cơ bản', 'Nguyễn Văn A', 1500000.00, 20),
('Khóa học Demo SQL nâng cao', 'Trần Thị B', 2500000.00, 45),
('Java Web Fullstack', 'Lê Văn C', 5000000.00, 100),
('Python cho người mới', 'Phạm Minh D', 800000.00, 35),
('Thiết kế giao diện Demo', 'Hoàng Thị E', 1200000.00, 15),
('Phân tích dữ liệu với sql', 'Lý Văn F', 1800000.00, 40);


UPDATE Course 
SET price = price * 1.15 
WHERE duration > 30;

DELETE FROM Course 
WHERE title LIKE '%Demo%';


SELECT * FROM Course 
WHERE title ILIKE '%SQL%';


SELECT * FROM Course 
WHERE price BETWEEN 500000 AND 2000000 
ORDER BY price DESC 
LIMIT 3;