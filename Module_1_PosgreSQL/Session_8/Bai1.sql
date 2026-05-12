create schema session08
set search_path to session08


CREATE TABLE order_detail (
    id SERIAL PRIMARY KEY,
    order_id INT,
    product_name VARCHAR(100),
    quantity INT,
    unit_price NUMERIC
);


INSERT INTO order_detail (order_id, product_name, quantity, unit_price) VALUES
(1, 'Laptop Dell', 1, 20000000),
(1, 'Chuột không dây', 2, 500000),
(2, 'Bàn phím cơ', 1, 1500000);

CREATE OR REPLACE PROCEDURE calculate_order_total(
    order_id_input INT, 
    OUT total NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT SUM(quantity * unit_price)
    INTO total
    FROM order_detail
    WHERE order_id = order_id_input;
    IF total IS NULL THEN
        total := 0;
    END IF;
END;
$$;

CALL calculate_order_total(1, NULL);

CALL calculate_order_total(99, NULL);
