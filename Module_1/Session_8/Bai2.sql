create schema session07;
set search_path to session07

CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(customer_id),
    total_amount DECIMAL(10,2),
    order_date DATE
);

INSERT INTO customer (full_name, email, phone) VALUES 
('Nguyễn Văn An', 'an@gmail.com', '0901234567'),
('Trần Thị Bình', 'binh@gmail.com', '0912345678'),
('Lê Văn Cường', 'cuong@gmail.com', '0923456789');

INSERT INTO orders (customer_id, total_amount, order_date) VALUES 
(1, 1500000.00, '2024-01-15'),
(2, 500000.00, '2024-01-20'),
(1, 2000000.00, '2024-02-10'),
(3, 1200000.00, '2024-02-25'),
(2, 300000.00, '2024-03-05');


CREATE VIEW v_order_summary AS
SELECT c.full_name, o.total_amount, o.order_date
FROM customer c
JOIN orders o ON c.customer_id = o.customer_id;


SELECT * FROM v_order_summary;


CREATE VIEW v_high_value_orders AS
SELECT * FROM orders 
WHERE total_amount >= 1000000;

SELECT * FROM v_high_value_orders;


UPDATE v_high_value_orders 
SET total_amount = 1100000 
WHERE order_id = 4;


CREATE VIEW v_monthly_sales AS
SELECT 
    TO_CHAR(order_date, 'YYYY-MM') AS month, 
    SUM(total_amount) AS total_revenue
FROM orders
GROUP BY month
ORDER BY month;


SELECT * FROM v_monthly_sales;


DROP VIEW v_order_summary;



-- 1. DROP VIEW:
--     Dùng để xóa các View ảo
--     Những view này không lưu trữ dữ liệu vật lý, mỗi khi SELECT, nó sẽ chạy lại truy vấn gốc.

-- 2. DROP MATERIALIZED VIEW:
--     Dùng để xóa các View vật lý
--     Những view này CÓ lưu trữ dữ liệu thực tế trên ổ đĩa (giống như một bảng tạm).
--     Khi xóa, hệ thống sẽ giải phóng cả không gian bộ nhớ mà dữ liệu đó đang chiếm dụng.