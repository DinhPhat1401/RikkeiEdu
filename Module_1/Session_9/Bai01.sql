create schema session09;
set search_path to session09;


CREATE TABLE Orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total_amount NUMERIC(10,2)
);


-- Em tham khảo AI để insert 100000 dòng record
INSERT INTO Orders (customer_id, order_date, total_amount)
SELECT 
    (random() * 1000)::INT, 
    CURRENT_DATE - (random() * 365)::INT * INTERVAL '1 day',
    (random() * 5000)::NUMERIC
FROM generate_series(1, 100000);


EXPLAIN ANALYZE 
SELECT * FROM Orders WHERE customer_id = 500;
-- trước khi tạo index Execution Time: 2.457 ms

CREATE INDEX idx_orders_customer_id ON Orders(customer_id);

EXPLAIN ANALYZE 
SELECT * FROM Orders WHERE customer_id = 500;
-- sau khi tạo index Execution Time: 0.153 ms