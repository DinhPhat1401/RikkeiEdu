set search_path to session06

CREATE TABLE OrderInfo (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total NUMERIC(10,2),
    status VARCHAR(20)
);

INSERT INTO OrderInfo (customer_id, order_date, total, status) VALUES
(1, '2024-10-15', 600000.00, 'Completed'),
(2, '2024-10-20', 450000.00, 'Pending'),
(3, '2024-11-05', 750000.00, 'Shipping'),
(4, '2024-10-01', 300000.00, 'Completed'),
(5, '2024-12-12', 1200000.00, 'Cancelled');

-- 2. Truy vấn các đơn hàng có tổng tiền lớn hơn 500,000
SELECT * FROM OrderInfo 
WHERE total > 500000;

-- 3. Truy vấn các đơn hàng có ngày đặt trong tháng 10 năm 2024
SELECT * FROM OrderInfo 
WHERE order_date BETWEEN '2024-10-01' AND '2024-10-31';

-- 4. Liệt kê các đơn hàng có trạng thái khác “Completed”
SELECT * FROM OrderInfo 
WHERE status <> 'Completed'; 

-- 5. Lấy 2 đơn hàng mới nhất (dựa trên ngày đặt hàng)
SELECT * FROM OrderInfo 
ORDER BY order_date DESC 
LIMIT 2;