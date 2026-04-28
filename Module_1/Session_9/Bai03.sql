create schema session09;
set search_path to session09;


CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    category_id INT,
    price NUMERIC(10,2),
    stock_quantity INT
);


INSERT INTO Products (category_id, price, stock_quantity)
SELECT 
    (random() * 20 + 1)::INT, 
    (random() * 1000 + 10)::NUMERIC,
    (random() * 500)::INT
FROM generate_series(1, 100000);




EXPLAIN ANALYZE 
SELECT * FROM Products 
WHERE category_id = 5 
ORDER BY price;

CREATE INDEX idx_products_category ON Products(category_id);
CLUSTER Products USING idx_products_category;

CREATE INDEX idx_products_price ON Products(price);

EXPLAIN ANALYZE 
SELECT * FROM Products 
WHERE category_id = 5 
ORDER BY price;
