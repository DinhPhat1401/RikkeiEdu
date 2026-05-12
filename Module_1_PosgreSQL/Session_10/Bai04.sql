set search_path to session10

-- select * from products
-- alter table products drop column last_modified;
-- alter table products add column stock int;

-- select * from orders
-- alter table orders drop column customer_id;
-- alter table orders drop column order_amount;
-- alter table orders add column product_id INT REFERENCES products(id);
-- alter table orders add column quantity INT;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;


CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    stock INT
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id),
    quantity INT
);


INSERT INTO products (name, stock) VALUES ('Laptop', 10), ('Bàn phím', 20);

CREATE OR REPLACE FUNCTION fn_update_inventory()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE products 
        SET stock = stock - NEW.quantity 
        WHERE id = NEW.product_id;

    ELSIF (TG_OP = 'UPDATE') THEN
        UPDATE products 
        SET stock = stock + OLD.quantity - NEW.quantity 
        WHERE id = NEW.product_id;


    ELSIF (TG_OP = 'DELETE') THEN
        UPDATE products 
        SET stock = stock + OLD.quantity 
        WHERE id = OLD.product_id;
    END IF;
    RETURN NULL; 
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_manage_stock
AFTER INSERT OR UPDATE OR DELETE ON orders
FOR EACH ROW 
EXECUTE FUNCTION fn_update_inventory();

INSERT INTO orders (product_id, quantity) VALUES (1, 3);
SELECT * FROM products WHERE id = 1; -- ban đầu chèn 10, sau khi mua 3 còn 7

UPDATE orders SET quantity = 1 WHERE id = 1;
SELECT * FROM products WHERE id = 1; -- đổi ý mua 1 thì trả lại 2 và -1

DELETE FROM orders WHERE id = 1;
SELECT * FROM products WHERE id = 1; -- xóa luôn hong mua nữa thì trong kho trở lại 10