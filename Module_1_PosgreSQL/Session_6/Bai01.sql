create schema session06
set search_path to session06


CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price NUMERIC(10,2),
    stock INT
);

INSERT INTO Product (name, category, price, stock) VALUES
('Laptop Dell XPS', 'Điện tử', 25000000.00, 10),
('iPhone 13 Mini', 'Điện tử', 9500000.00, 5),
('Máy tính bảng Galaxy Tab', 'Điện tử', 7000000.00, 15),
('Tủ lạnh LG', 'Gia dụng', 12000000.00, 3),
('Lò vi sóng Sharp', 'Gia dụng', 2500000.00, 20);


----------------
SELECT * FROM Product;

-----------------
SELECT * FROM Product ORDER BY price DESC LIMIT 3;
-------------------

SELECT * FROM Product 
WHERE category = 'Điện tử' AND price < 10000000;
--------------------

SELECT * FROM Product ORDER BY stock ASC;
