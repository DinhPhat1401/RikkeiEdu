set search_path to session09;


CREATE OR REPLACE PROCEDURE calculate_total_sales(
    start_date DATE, 
    end_date DATE, 
    OUT total NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT SUM(amount)
    INTO total
    FROM Sales
    WHERE sale_date BETWEEN start_date AND end_date;
    IF total IS NULL THEN
        total := 0;
    END IF;
END;
$$;

DO $$
DECLARE
    v_revenue_report NUMERIC; -- Biến để hứng kết quả từ Procedure
BEGIN
   
    CALL calculate_total_sales('2024-01-01', '2024-01-10', v_revenue_report);
    RAISE NOTICE 'Tổng doanh thu trong khoảng thời gian chọn là: %', v_revenue_report;
END $$;


