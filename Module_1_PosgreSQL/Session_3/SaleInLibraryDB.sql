CREATE SCHEMA sales; 

CREATE TABLE sales.Products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2),
    stock_quantity INTEGER
);

CREATE TABLE sales.Orders (
    order_id SERIAL PRIMARY KEY,
    order_date DATE DEFAULT CURRENT_DATE,
    member_id INTEGER,
    CONSTRAINT fk_orders_members 
        FOREIGN KEY (member_id) 
        REFERENCES library.Members(member_id)
);

CREATE TABLE sales.OrderDetails (
    order_detail_id SERIAL PRIMARY KEY,
    order_id INTEGER,
    product_id INTEGER,
    quantity INTEGER,
    CONSTRAINT fk_details_orders 
        FOREIGN KEY (order_id) 
        REFERENCES sales.Orders(order_id),
    CONSTRAINT fk_details_products 
        FOREIGN KEY (product_id) 
        REFERENCES sales.Products(product_id)
);