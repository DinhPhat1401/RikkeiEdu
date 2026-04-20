
set search_path to session06

CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    points INT
);

INSERT INTO Customer (name, email, phone, points) VALUES
('Nguyễn Văn A', 'anguyen@gmail.com', '0901234567', 500),
('Trần Thị B', 'btran@gmail.com', '0912345678', 850),
('Lê Văn C', NULL, '0923456789', 300),
('Phạm Minh D', 'dpham@gmail.com', '0934567890', 1200),
('Nguyễn Văn A', 'an.v2@gmail.com', '0945678901', 450),
('Hoàng Thị E', 'ehoang@gmail.com', '0956789012', 950),
('Lý Văn F', 'fly@exgmailample.com', '0967890123', 600);


------------
SELECT DISTINCT name FROM Customer;

SELECT * FROM Customer 
WHERE email IS NULL;

SELECT * FROM Customer 
ORDER BY points DESC 
LIMIT 3 OFFSET 1;

SELECT * FROM Customer 
ORDER BY name DESC;