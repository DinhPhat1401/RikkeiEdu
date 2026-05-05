set search_path to session10;

CREATE TABLE customers(
 	id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	credit_limit numeric(10,0)
);

CREATE TABLE orders(
	id SERIAL PRIMARY Key,
	customer_id int not null REFERENCES customers(id),
	order_amount numeric (10,0)
	
);

insert into customers (name, credit_limit) values ('Nguyen Van A', 1000000000),
												('Nguyen Van B', 2000000000),
												('Nguyen Van C', 1500000000);




CREATE OR REPLACE FUNCTION check_credit_limit()
RETURNS TRIGGER AS $$
DECLARE
    v_current_total NUMERIC;
    v_credit_limit NUMERIC;
BEGIN
    SELECT credit_limit INTO v_credit_limit 
    FROM customers 
    WHERE id = NEW.customer_id;
    SELECT SUM(order_amount) INTO v_current_total 
    FROM orders 
    WHERE customer_id = NEW.customer_id;
    IF (v_current_total + NEW.order_amount) > v_credit_limit THEN
        RAISE EXCEPTION 'Không thể tạo đơn hàng! Vượt hạn mức tín dụng (Hạn mức: %, Tổng sau khi thêm: %)', 
            v_credit_limit, (v_current_total + NEW.order_amount);
    END IF;
	
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_credit
BEFORE INSERT ON orders
FOR EACH ROW
EXECUTE FUNCTION check_credit_limit();

INSERT INTO orders (customer_id, order_amount) VALUES (3, 3000000);

select * from orders
-- Đơn hàng đã được thêm


INSERT INTO orders (customer_id, order_amount) VALUES (3, 3000000000);
-- ERROR:  Không thể tạo đơn hàng! Vượt hạn mức tín dụng (Hạn mức: 1500000000, Tổng sau khi thêm: 3003000000)