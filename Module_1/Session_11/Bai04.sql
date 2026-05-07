SET search_path TO session11;

DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100),
    balance NUMERIC(12,2)
);

CREATE TABLE transactions (
    trans_id SERIAL PRIMARY KEY,
    account_id INT REFERENCES accounts(account_id),
    amount NUMERIC(12,2),
    trans_type VARCHAR(20),
    created_at TIMESTAMP DEFAULT NOW()
);


INSERT INTO accounts (customer_name, balance) VALUES ('Pham Do Dinh Phat', 1000.00);
-----------------

BEGIN; 

UPDATE accounts 
SET balance = balance - 200 
WHERE account_id = 1 AND balance >= 200;

INSERT INTO transactions (account_id, amount, trans_type)
VALUES (1, 200, 'Rút tiền');

COMMIT;


SELECT * FROM accounts;
SELECT * FROM transactions;

-----------------
BEGIN;

UPDATE accounts 
SET balance = balance - 200 
WHERE account_id = 1;

-- Bước 2: Cố tình ghi log sai account_id 
INSERT INTO transactions (account_id, amount, trans_type)
VALUES (999, 200, 'Rút Tiền'); 
-- ERROR:  insert or update on table "transactions" violates foreign key constraint "transactions_account_id_fkey"
-- Key (account_id)=(999) is not present in table "accounts". 

ROLLBACK;

SELECT * FROM accounts;

---------------
BEGIN; 

UPDATE accounts 
SET balance = balance - 200 
WHERE account_id = 1 AND balance >= 200;

INSERT INTO transactions (account_id, amount, trans_type)
VALUES (1, 200, 'Rút tiền');

COMMIT;

BEGIN; 

UPDATE accounts 
SET balance = balance - 200 
WHERE account_id = 1 AND balance >= 200;

INSERT INTO transactions (account_id, amount, trans_type)
VALUES (1, 200, 'Rút tiền');

COMMIT;
