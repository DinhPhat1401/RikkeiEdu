CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100),
    city VARCHAR(50)
);


CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id),
    order_date DATE,
    total_amount NUMERIC(10,2)
);

CREATE TABLE order_items (
    item_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    product_name VARCHAR(100),
    quantity INT,
    price NUMERIC(10,2)
);


INSERT INTO customers (customer_name, city) VALUES 
('Nguyễn Văn A', 'Hà Nội'),
('Trần Thị B', 'Đà Nẵng'),
('Lê Văn C', 'Hồ Chí Minh'),
('Phạm Thị D', 'Hà Nội');


INSERT INTO orders (customer_id, order_date, total_amount) VALUES 
(1, '2024-12-20', 12000.00), -- Khách 1 tại Hà Nội có doanh thu > 10000
(2, '2025-01-05', 1500.00),
(1, '2025-02-10', 2500.00),
(3, '2025-02-15', 4000.00),
(4, '2025-03-01', 800.00);

INSERT INTO order_items (order_id, product_name, quantity, price) VALUES 
(1, 'Laptop Gaming', 1, 12000.00),
(2, 'Bàn phím cơ', 1, 1500.00),
(3, 'Chuột không dây', 5, 500.00),
(4, 'Màn hình 24 inch', 2, 2000.00);



SELECT 
    c.customer_name AS "Tên khách", 
    o.order_date AS "Ngày đặt hàng", 
    o.total_amount AS "Tổng tiền"
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id;


-------------

SELECT 
    SUM(total_amount) AS "Tổng doanh thu",
    AVG(total_amount) AS "Trung bình đơn hàng",
    MAX(total_amount) AS "Đơn hàng lớn nhất",
    MIN(total_amount) AS "Đơn hàng nhỏ nhất",
    COUNT(order_id) AS "Số lượng đơn hàng"
FROM orders;

-------------

SELECT 
    c.city, 
    SUM(o.total_amount) AS "Tổng doanh thu theo thành phố"
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.city
HAVING SUM(o.total_amount) > 10000;

------------------

SELECT 
    c.customer_name, 
    o.order_date, 
    oi.product_name,
    oi.quantity, 
    oi.price
FROM customers c
INNER JOIN orders o ON c.customer_id = o.customer_id
INNER JOIN order_items oi ON o.order_id = oi.order_id;


-------------
SELECT c.customer_name, SUM(o.total_amount) AS total_revenue
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING SUM(o.total_amount) = (
    SELECT MAX(revenue_per_customer)
    FROM (
        SELECT SUM(total_amount) AS revenue_per_customer
        FROM orders
        GROUP BY customer_id
    ) AS sub_table
);