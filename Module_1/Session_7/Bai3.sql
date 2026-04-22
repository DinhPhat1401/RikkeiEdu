create schema session07;
set search_path to session07

CREATE TABLE post (
    post_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    content TEXT,
    tags TEXT[],
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_public BOOLEAN DEFAULT TRUE
);


CREATE TABLE post_like (
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id)
);

-- SQL INSER GEN ra lượng lớn bản ghi em tham khảo nguồn GERMINI
INSERT INTO post (user_id, content, tags, created_at, is_public)
SELECT 
    (random() * 1000)::INT,
    CASE 
        WHEN s.i % 10 = 0 THEN 'Đi du lịch Đà Lạt thật tuyệt vời'
        WHEN s.i % 10 = 1 THEN 'Món ăn này rất ngon'
        ELSE 'Nội dung bài đăng số ' || s.i
    END,
    CASE 
        WHEN s.i % 3 = 0 THEN ARRAY['travel', 'nature']
        WHEN s.i % 3 = 1 THEN ARRAY['food', 'cooking']
        ELSE ARRAY['daily', 'life']
    END,
    NOW() - (random() * INTERVAL '30 days'),
    (random() > 0.2) -- 80% là public
FROM generate_series(1, 10000) AS s(i);
----------------------

EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE is_public = TRUE AND LOWER(content) LIKE '%du lịch%';
-- Seq Scan với Execution Time là 3.392
CREATE INDEX idx_post_content_lower ON post (LOWER(content));

EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE is_public = TRUE AND LOWER(content) LIKE '%du lịch%';



---------------
EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE tags @> ARRAY['travel']; 
-- Seq Scan với Execution Time là 2.724
CREATE INDEX idx_post_tags_gin ON post USING GIN (tags);
EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE tags @> ARRAY['travel']; 
--   Bitmap Index Scan với Execution Time: 0.430 ms
-- Nhanh hơn rất nhiều so với khi chưa tạo Index cho cột tags

-------------------------------
EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE is_public = TRUE AND created_at >= NOW() - INTERVAL '7 days';
-- Seq Scan on post  (cost=0.00..339.00 rows=1931 width=92) (actual time=0.022..1.133 rows=1856.00 loops=1)
-- Execution Time: 1.181 ms
CREATE INDEX idx_post_recent_public 
ON post(created_at DESC) 
WHERE is_public = TRUE;


EXPLAIN ANALYZE 
SELECT * FROM post 
WHERE is_public = TRUE AND created_at >= NOW() - INTERVAL '7 days';
--   ->  Bitmap Index Scan on idx_post_recent_public  (cost=0.00..38.77 rows=1931 width=0) (actual time=0.116..0.116 rows=1856.00 loops=1)
-- Execution Time: 0.400 ms
