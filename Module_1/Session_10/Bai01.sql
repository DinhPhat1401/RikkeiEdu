create schema session10;
set search_path to session10;

drop table if EXISTS   products;
create table products(
	id serial primary key,
	p_name varchar(100),
	price numeric(9,0),
	last_modified timestamp default now()

	-- created_raw timestamptz DEFAULT now(),
 --    -- Cột hiển thị (tự động format theo cột gốc)
 --    created_at text GENERATED ALWAYS AS (to_char(created_raw, 'DD/MM/YYYY HH24:MI:SS')) STORED
);

insert into products (p_name, price) values ('Test 01',100000);

CREATE OR REPLACE FUNCTION update_last_modified() 
RETURNS TRIGGER AS $$
BEGIN
    NEW.last_modified = now();
    RETURN NEW;              
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER trg_update_last_modified 
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION update_last_modified();

select * from products
-- 2026-04-28 21:06:17.745281
update products  set p_name = 'Test 02' where products.id = 1;
select * from products
-- 2026-04-28 21:06:52.683032






