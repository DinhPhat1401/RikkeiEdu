create schema session07;
set search_path to session07

CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    region VARCHAR(50)
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(customer_id),
    total_amount DECIMAL(10,2),
    order_date DATE,
    status VARCHAR(20)
);

CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2),
    category VARCHAR(50)
);

CREATE TABLE order_detail (
    order_id INT REFERENCES orders(order_id),
    product_id INT REFERENCES product(product_id),
    quantity INT,
    PRIMARY KEY (order_id, product_id)
);

INSERT INTO customer (full_name, region) VALUES 
('An Nguyễn', 'North'), ('Bình Trần', 'North'),
('Chi Lê', 'South'), ('Đức Phạm', 'South'),
('Hoa Hoàng', 'Central'), ('Lan Đặng', 'West');

INSERT INTO orders (customer_id, total_amount, order_date, status) VALUES 
(1, 5000.00, '2024-01-10', 'Completed'),
(2, 3000.00, '2024-01-15', 'Completed'), 
(3, 10000.00, '2024-02-01', 'Completed'),
(4, 2000.00, '2024-02-05', 'Completed'), 
(5, 4000.00, '2024-02-10', 'Completed'), 
(6, 1000.00, '2024-03-01', 'Completed');  


------------------

CREATE VIEW v_revenue_by_region AS
SELECT c.region, SUM(o.total_amount) AS total_revenue
FROM customer c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.region;


SELECT * FROM v_revenue_by_region
ORDER BY total_revenue DESC
LIMIT 3;


CREATE VIEW v_revenue_above_avg AS
SELECT * FROM v_revenue_by_region
WHERE total_revenue > (

    SELECT AVG(total_revenue) 
    FROM v_revenue_by_region
);


SELECT * FROM v_revenue_above_avg;
