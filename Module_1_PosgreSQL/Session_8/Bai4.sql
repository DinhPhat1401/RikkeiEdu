create schema session08;
set search_path to session08;


CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    price NUMERIC,
    discount_percent INT
);

INSERT INTO products (name, price, discount_percent) VALUES
('Laptop Gaming', 20000000, 10),
('Tai nghe Bluetooth', 2000000, 60),
('Bàn phím cơ', 1500000, 30);

CREATE OR REPLACE PROCEDURE calculate_discount(
    p_id INT, 
    OUT p_final_price NUMERIC
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_price NUMERIC;
    v_discount INT;
BEGIN
    SELECT price, discount_percent INTO v_price, v_discount
    FROM products
    WHERE id = p_id;
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Không tìm thấy sản phẩm có ID %', p_id;
    END IF;

    IF v_discount > 50 THEN
        v_discount := 50;
        RAISE NOTICE 'Sản phẩm ID % có mức giảm giá vượt mức (%), đã giới hạn về 50%%.', p_id, v_discount;
    END IF;

    p_final_price := v_price - (v_price * v_discount / 100.0);

    UPDATE products
    SET price = p_final_price
    WHERE id = p_id;
    
    RAISE NOTICE 'Cập nhật giá sản phẩm ID % thành công.', p_id;
END;
$$;

DO $$
DECLARE
    v_result NUMERIC;
BEGIN
    CALL calculate_discount(2, v_result);

END $$;

SELECT * FROM products WHERE id = 2;