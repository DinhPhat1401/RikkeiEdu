set search_path to session10;


CREATE TABLE employees(
	id SERIAL PRIMARY KEY,
	name varchar(100) not null,
	position varchar(100),
	salary decimal
	
);
CREATE TABLE employees_log (
    log_id SERIAL PRIMARY KEY,
    employee_id INT,
    operation VARCHAR(10), 
    old_data JSONB,       
    new_data JSONB,      
    change_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION fn_log_employee_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO employees_log (employee_id, operation, new_data)
        VALUES (NEW.id, 'INSERT', to_jsonb(NEW));
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO employees_log (employee_id, operation, old_data, new_data)
        VALUES (NEW.id, 'UPDATE', to_jsonb(OLD), to_jsonb(NEW));
    ELSIF (TG_OP = 'DELETE') THEN
        INSERT INTO employees_log (employee_id, operation, old_data)
        VALUES (OLD.id, 'DELETE', to_jsonb(OLD));
    END IF;
    RETURN NULL; 
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_employee_audit
AFTER INSERT OR UPDATE OR DELETE ON employees
FOR EACH ROW EXECUTE FUNCTION fn_log_employee_changes();



INSERT INTO employees (name, position, salary) 
VALUES ('Phát', 'Backend Developer', 15000000);


UPDATE employees 
    SET salary = 18000000 
    WHERE name = 'Phát';
	
    DELETE FROM employees WHERE name = 'Phát';

    SELECT * FROM employees_log ORDER BY change_time;


