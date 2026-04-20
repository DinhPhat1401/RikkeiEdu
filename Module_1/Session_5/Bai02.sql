set search_path to session05;


SELECT p.product_name, SUM(o.total_price) AS total_revenue
FROM products p
JOIN orders o ON p.product_id = o.product_id
GROUP BY p.product_name
HAVING SUM(o.total_price) = (
    SELECT MAX(revenue_per_product)
    FROM (
        SELECT SUM(total_price) AS revenue_per_product
        FROM orders
        GROUP BY product_id
    ) AS sub_table
);

SELECT 
    p.category, 
    SUM(o.total_price) AS total_revenue
FROM 
    products p
JOIN 
    orders o ON p.product_id = o.product_id
GROUP BY 
    p.category;