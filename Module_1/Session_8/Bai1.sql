create schema session07;
set search_path to session07


CREATE TABLE book (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100),
    genre VARCHAR(50),
    price DECIMAL(10,2),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Em tham khảo nguồn Germini để insert tự động generate 50k bản ghi

INSERT INTO book (title, author, genre, price, description)
SELECT 
    'Book Title ' || i,
    CASE 
        WHEN i % 100 = 0 THEN 'J.K. Rowling' 
        ELSE 'Author ' || i 
    END,
    CASE 
        WHEN i % 5 = 0 THEN 'Fantasy'
        WHEN i % 5 = 1 THEN 'Sci-Fi'
        ELSE 'Romance'
    END,
    (random() * 100)::DECIMAL(10,2),
    'This is a long description for book number ' || i || '. It contains some keywords for full-text search.'
FROM generate_series(1, 50000) s(i); 



--chưa dùng tạo và dùng index
EXPLAIN ANALYZE SELECT * FROM book WHERE genre = 'Fantasy';
-- sau khi thực thi cho ra "Seq Scan" với Execution Time là 3.306ms
EXPLAIN ANALYZE SELECT * FROM book WHERE author ILIKE '%Rowling%';
-- sau khi thực thi cho ra "Seq Scan" với Execution Time là 14.454ms


-- Bắt đầu tạo và dùng index
CREATE INDEX idx_book_genre ON book(genre);

CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX idx_book_author_trgm ON book USING gin (author gin_trgm_ops); 

EXPLAIN ANALYZE SELECT * FROM book WHERE genre = 'Fantasy';
-- Sau khi thực thi cho ra "Bitmap Index Scan" vtốới Execution Time là 1.701ms
EXPLAIN ANALYZE SELECT * FROM book WHERE author ILIKE '%Rowling%';
-- Sau khi thực thi cho ra "Bitmap Index Scan" với Execution Time là 3.005ms

-- Thấy rõ sự chênh lệch về tốc độ thực thi câu lệnh select trước và sau là vô cùng lớn. Dùng Index nhanh đến gần 3% so với không dùng Index


CLUSTER book USING idx_book_genre;
EXPLAIN ANALYZE SELECT * FROM book WHERE genre = 'Fantasy';
-- Sau khi cluster thì câu lệnh có tốc độ Execution Time = 0.949. Đã nhanh hơn so với khi chưa CLUSTER
