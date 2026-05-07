SET search_path TO session11;
drop table products;

CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100),
    stock INT,
    price NUMERIC(10,2)
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100),
    total_amount NUMERIC(10,2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE order_items (
    order_item_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    product_id INT REFERENCES products(product_id),
    quantity INT,
    subtotal NUMERIC(10,2)
);


INSERT INTO products (product_name, stock, price) 
VALUES ('iPhone 15', 5, 2000), ('AirPods', 10, 500);


-------------------
BEGIN; 

UPDATE products SET stock = stock - 2 WHERE product_id = 1 AND stock >= 2;

UPDATE products SET stock = stock - 1 WHERE product_id = 2 AND stock >= 1;

INSERT INTO orders (customer_name, total_amount) 
VALUES ('Nguyen Van A', (2 * 2000.00) + (1 * 500.00));

INSERT INTO order_items (order_id, product_id, quantity, subtotal)
VALUES (currval('orders_order_id_seq'), 1, 2, 4000.00),
       (currval('orders_order_id_seq'), 2, 1, 500.00);

COMMIT;

SELECT * FROM products;
SELECT * FROM orders;
---------------------------------
-- cho hết hàng trong kho
UPDATE products SET stock = 0 WHERE product_id = 2;

-- SELECT * FROM products



BEGIN;
-- 1. Trừ kho iPhone (Lệnh này sẽ thành công tạm thời trong Transaction)
UPDATE products 
SET stock = stock - 2 
WHERE product_id = 1 AND stock >= 2;

-- 2. Trừ kho AirPods (Lệnh này sẽ THẤT BẠI vì kho đang bằng 0)
DO $$
BEGIN
    IF (SELECT stock FROM products WHERE product_id = 2) < 1 THEN
        RAISE EXCEPTION 'LỖI: AirPods hết hàng!';
    END IF;
END $$;

ROLLBACK;


SELECT * FROM products;
--- Ta thấy nếu 1 trong 2 câu lệnh ở trên có lỗi, nhờ có transaction mà ta có thể roll back trả lại dữ liệu cho những update trước đó
