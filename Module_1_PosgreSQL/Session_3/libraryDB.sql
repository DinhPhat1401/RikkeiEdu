--Đã ở session 2 em đã tạo db library rồi. Bảng đó dư price nhưng thiếu available
alter table library.Books drop column price
alter table library.Books add column available BOOLEAN DEFAULT TRUE


CREATE TABLE library.Members (
    member_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    join_date DATE DEFAULT CURRENT_DATE
);