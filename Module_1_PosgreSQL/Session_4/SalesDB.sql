
CREATE SCHEMA session04;


CREATE TABLE session04.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    category VARCHAR(50),
    price DECIMAL(10,2),
    stock INT
);


INSERT INTO session04.products (name, category, price, stock) VALUES
('Laptop Dell', 'Electronics', 1500.00, 5),
('Chuột Logitech', 'Electronics', 25.50, 50),
('Bàn phím Razer', 'Electronics', 120.00, 20),
('Tủ lạnh LG', 'Home Appliances', 800.00, 3),
('Máy giặt Samsung', 'Home Appliances', 600.00, 2);

INSERT INTO session04.products (name, category, price, stock) 
VALUES ('Điều hòa Panasonic', 'Home Appliances', 400.00, 10);

UPDATE session04.products 
SET stock = 7 
WHERE name = 'Laptop Dell';

DELETE FROM session04.products 
WHERE stock = 0;

SELECT * FROM session04.products
ORDER BY price ASC;

SELECT DISTINCT category 
FROM session04.products;

SELECT * FROM session04.products 
WHERE price BETWEEN 100 AND 1000;

SELECT * FROM session04.products 
WHERE name ILIKE '%LG%' OR name ILIKE '%Samsung%';

SELECT * FROM session04.products 
ORDER BY price DESC 
LIMIT 2;

SELECT * FROM session04.products 
ORDER BY id ASC 
LIMIT 2 OFFSET 1;






