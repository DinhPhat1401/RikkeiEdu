create schema session09;
set search_path to session09;


CREATE TABLE Sales (
    sale_id SERIAL PRIMARY KEY,
    customer_id INT,
    product_id INT,
    sale_date DATE,
    amount NUMERIC
);


INSERT INTO Sales (customer_id, product_id, sale_date, amount) VALUES
(1, 101, '2024-01-01', 500),
(1, 102, '2024-01-05', 700), 
(2, 101, '2024-01-10', 300), 
(3, 103, '2024-01-15', 1500);



-- Yêu cầu 1: Tạo View tổng hợp
CREATE OR REPLACE VIEW CustomerSales AS
SELECT customer_id, SUM(amount) AS total_amount
FROM Sales
GROUP BY customer_id;

SELECT * FROM CustomerSales WHERE total_amount > 1000;

-- Vì không thể cập nhật bảng thông qua view nếu như view có các lệnh như Join, Group By,...



