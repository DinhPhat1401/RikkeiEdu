create schema session09;
SET search_path TO session09;

DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    price NUMERIC,
    category_id INT
);

INSERT INTO Products (name, price, category_id) VALUES
('Bàn gỗ', 1000, 1),
('Ghế xoay', 500, 1),
('Laptop', 2000, 2);


CREATE OR REPLACE PROCEDURE update_product_price(
    p_category_id INT, 
    p_increase_percent NUMERIC
)
LANGUAGE plpgsql
AS $$
DECLARE
    rec RECORD;         
    v_new_price NUMERIC; 
BEGIN
    FOR rec IN SELECT product_id, price FROM Products WHERE category_id = p_category_id 
    LOOP
        v_new_price := rec.price * (1 + p_increase_percent / 100);
        UPDATE Products 
        SET price = v_new_price 
        WHERE product_id = rec.product_id;
    END LOOP;
END;
$$;

CALL update_product_price(1, 10);

SELECT * FROM Products;