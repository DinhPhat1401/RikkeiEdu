CREATE DATABASE "SalesDB";

CREATE SCHEMA sales;

CREATE TABLE sales.Customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE sales.Products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price NUMERIC NOT NULL,
    stock_quantity INTEGER NOT NULL
);

CREATE TABLE sales.Orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    order_date DATE NOT NULL,
    CONSTRAINT fk_orders_customers 
        FOREIGN KEY (customer_id) 
        REFERENCES sales.Customers(customer_id)
);

CREATE TABLE sales.OrderItems (
    order_item_id SERIAL PRIMARY KEY,
    order_id INTEGER,
    product_id INTEGER,
    quantity INTEGER NOT NULL CHECK (quantity >= 1),
    CONSTRAINT fk_orderitems_orders 
        FOREIGN KEY (order_id) 
        REFERENCES sales.Orders(order_id),
    CONSTRAINT fk_orderitems_products 
        FOREIGN KEY (product_id) 
        REFERENCES sales.Products(product_id)
);