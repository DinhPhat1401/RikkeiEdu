set search_path to session06

CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO Customer (name) VALUES 
('Nguyễn Văn An'), 
('Trần Thị Bình'), 
('Lê Văn Cường'), 
('Phạm Minh Đức');

INSERT INTO Orders (customer_id, order_date, total_amount) VALUES
(1, '2024-01-10', 500000.00),
(1, '2024-02-15', 1500000.00),
(2, '2024-03-20', 5000000.00), 
(3, '2024-04-05', 1000000.00);


SELECT c.name, SUM(o.total_amount) AS total_spent
FROM Customer c
JOIN Orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
ORDER BY total_spent DESC;


SELECT c.name, SUM(o.total_amount) AS total_spent
FROM Customer c
JOIN Orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
HAVING SUM(o.total_amount) = (
    SELECT MAX(customer_total)
    FROM (
        SELECT SUM(total_amount) AS customer_total
        FROM Orders
        GROUP BY customer_id
    ) AS sub_table
);


SELECT c.name
FROM Customer c
LEFT JOIN Orders o ON c.id = o.customer_id
WHERE o.id IS NULL;


SELECT c.name, SUM(o.total_amount) AS total_spent
FROM Customer c
JOIN Orders o ON c.id = o.customer_id
GROUP BY c.id, c.name
HAVING SUM(o.total_amount) > (
    SELECT AVG(customer_total)
    FROM (
        SELECT SUM(total_amount) AS customer_total
        FROM Orders
        GROUP BY customer_id
    ) AS sub_avg
);