set search_path to session04

alter table products add column manufacturer varchar (50)

INSERT INTO products (name, category, price, stock, manufacturer) VALUES
('Laptop Dell XPS 13', 'Laptop', 25000000, 12, 'Dell'),
('Chuột Logitech M90', 'Phụ kiện', 150000, 50, 'Logitech'),
('Bàn phím cơ Razer', 'Phụ kiện', 2200000, 0, 'Razer'),
('Macbook Air M2', 'Laptop', 32000000, 7, 'Apple'),
('iPhone 14 Pro Max', 'Điện thoại', 35000000, 15, 'Apple'),
('Laptop Dell XPS 13', 'Laptop', 25000000, 12, 'Dell'),
('Tai nghe AirPods 3', 'Phụ kiện', 4500000, NULL, 'Apple');

INSERT INTO products (name, category, price, stock, manufacturer) 
VALUES ('Chuột không dây Logitech M170', 'Phụ kiện', 300000, 20, 'Logitech');

UPDATE products 
SET price = price * 1.1 
WHERE manufacturer = 'Apple';

DELETE FROM products 
WHERE stock = 0;


SELECT * FROM products 
WHERE price BETWEEN 1000000 AND 30000000;

SELECT * FROM products 
WHERE stock IS NULL;

SELECT DISTINCT manufacturer 
FROM products;


SELECT * FROM products 
ORDER BY price DESC, name ASC;


SELECT * FROM products 
WHERE name ILIKE '%laptop%';

SELECT * FROM products 
ORDER BY price DESC 
LIMIT 2;