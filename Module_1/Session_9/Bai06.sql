set search_path to session06


CREATE TABLE Orders (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total_amount NUMERIC(10,2)
);


INSERT INTO Orders (customer_id, order_date, total_amount) VALUES
(1, '2024-05-10', 20000000.00),
(2, '2024-08-15', 35000000.00), 
(3, '2025-01-20', 10000000.00),
(4, '2025-03-12', 5000000.00),  
(1, '2026-02-14', 60000000.00), 
(5, '2026-04-01', 1500000.00);

-------------------------------------------------------------------


SELECT 
    SUM(total_amount) AS total_revenue,
    COUNT(id) AS total_orders,
    AVG(total_amount) AS average_order_value
FROM Orders;


SELECT 
    EXTRACT(YEAR FROM order_date) AS order_year,
    SUM(total_amount) AS yearly_revenue
FROM Orders
GROUP BY EXTRACT(YEAR FROM order_date)
HAVING SUM(total_amount) > 50000000;


SELECT * FROM Orders
ORDER BY total_amount DESC
LIMIT 5;