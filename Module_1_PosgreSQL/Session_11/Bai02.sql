CREATE SCHEMA session11;
SET search_path TO session11;

CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    owner_name VARCHAR(100),
    balance NUMERIC(10,2)
);

INSERT INTO accounts (owner_name, balance)
VALUES ('A', 500.00), ('B', 300.00);



BEGIN;
UPDATE accounts 
SET balance = balance - 100 
WHERE owner_name = 'A';

UPDATE accounts 
SET balance = balance + 100 
WHERE owner_name = 'B';
COMMIT;

SELECT * FROM accounts;


BEGIN;
UPDATE accounts 
SET balance = balance - 100 
WHERE owner_name = 'A';


UPDATE accounts 
SET balance = balance + 100 
WHERE account_id = 999; 
ROLLBACK;
SELECT * FROM accounts;