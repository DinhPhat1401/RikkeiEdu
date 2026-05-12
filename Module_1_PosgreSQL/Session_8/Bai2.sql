create schema session08;
set search_path to session08;



CREATE TABLE inventory (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100),
    quantity INT
);

INSERT INTO inventory (product_name, quantity) VALUES
('iPhone 15 Pro', 10),
('Samsung S24 Ultra', 2);


CREATE OR REPLACE PROCEDURE check_stock(p_id INT, p_qty INT)
LANGUAGE plpgsql
AS $$
DECLARE
    current_stock INT;
BEGIN
    -- Lấy số lượng tồn kho hiện tại dựa trên ID sản phẩm
    SELECT quantity INTO current_stock 
    FROM inventory 
    WHERE product_id = p_id;

    -- Kiểm tra nếu sản phẩm không tồn tại
    IF NOT FOUND THEN
        RAISE EXCEPTION 'Sản phẩm với ID % không tồn tại!', p_id;
    END IF;

    -- Kiểm tra tồn kho 
    IF current_stock < p_qty THEN
        RAISE EXCEPTION 'Không đủ hàng trong kho (Hiện có: %, Yêu cầu: %)', current_stock, p_qty;
    ELSE
        RAISE NOTICE 'Đủ hàng! Có thể tiến hành xuất kho.';
    END IF;
END;
$$;


CALL check_stock(1, 5);
CALL check_stock(2, 5);
