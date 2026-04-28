create schema session09;
set search_path to session09;


CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    username VARCHAR(50)
);

-- Em tham khảo AI để chèn 100000 bản ghi
INSERT INTO Users (email, username)
SELECT 
    'user' || i || '@example.com',
    'username_' || i
FROM generate_series(1, 100000) AS s(i);


CREATE INDEX idx_users_email_hash ON Users USING HASH (email);

EXPLAIN ANALYZE 
SELECT * FROM Users 
WHERE email = 'user50000@example.com';
-- Index Scan using idx_users_email_hash on users  (cost=0.00..8.02 rows=1 width=39) (actual time=0.014..0.015 rows=1.00 loops=1)
-- Execution Time: 0.025 ms (Rất nhanh)