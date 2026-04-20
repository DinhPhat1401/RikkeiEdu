create schema session05b;
set search_path to session05b;


CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    city VARCHAR(50)
);


CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id),
    order_date DATE,
    total_price INT
);


CREATE TABLE order_items (
    item_id INT PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    product_id INT,
    quantity INT,
    price INT
);

-- Chèn dữ liệu
INSERT INTO customers VALUES 
(1, 'Nguyễn Văn A', 'Hà Nội'),
(2, 'Trần Thị B', 'Đà Nẵng'),
(3, 'Lê Văn C', 'Hồ Chí Minh'),
(4, 'Phạm Thị D', 'Hà Nội');

INSERT INTO orders VALUES 
(101, 1, '2024-12-20', 3000),
(102, 2, '2025-01-05', 1500),
(103, 1, '2025-02-10', 2500),
(104, 3, '2025-02-15', 4000),
(105, 4, '2025-03-01', 800);

INSERT INTO order_items VALUES 
(1, 101, 1, 2, 1500),
(2, 102, 2, 1, 1500),
(3, 103, 3, 5, 500),
(4, 104, 2, 4, 1000);


SELECT 
    c.customer_name, 
    SUM(o.total_price) AS total_revenue, 
    COUNT(o.order_id) AS order_count
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING SUM(o.total_price) > 2000;


SELECT c.customer_name, SUM(o.total_price) AS total_revenue
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING SUM(o.total_price) > (
    SELECT AVG(customer_total)
    FROM (
        SELECT SUM(total_price) AS customer_total
        FROM orders
        GROUP BY customer_id
    ) AS avg_revenue
);

SELECT c.city, SUM(o.total_price) AS city_revenue
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.city
HAVING SUM(o.total_price) = (
    SELECT MAX(total_city_revenue)
    FROM (
        SELECT SUM(total_price) AS total_city_revenue
        FROM customers c2
        JOIN orders o2 ON c2.customer_id = o2.customer_id
        GROUP BY c2.city
    ) AS max_city
);


SELECT 
    c.customer_name, 
    c.city, 
    SUM(oi.quantity) AS total_products_bought, 
    SUM(oi.quantity * oi.price) AS total_spent
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
INNER JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY c.customer_id, c.customer_name, c.city
ORDER BY total_spent DESC;