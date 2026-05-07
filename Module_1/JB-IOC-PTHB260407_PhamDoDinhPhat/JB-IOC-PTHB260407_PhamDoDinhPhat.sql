-- PHẦN 1: Thao tác với dữ liệu các bảng
CREATE TABLE Passengers(
	passenger_id varchar(5) PRIMARY KEY,
	passenger_full_name VARCHAR(100) NOT NULL,
	passenger_email varchar(100) NOT NULL,
	passenger_phone varchar(15) NOT NULL,
	passenger_cccd varchar(20) NOT NULL
); 

CREATE TABLE Trains(
	train_id varchar(5) PRIMARY KEY,
	train_name varchar(100) NOT NULL,
	train_type VARCHAR(10) NOT NULL,
	total_seats INT NOT NULL
);

CREATE TABLE Tickets(
	ticket_id VARCHAR(5) PRIMARY KEY,
	passenger_id VARCHAR(5) NOT NULL ,
	train_id VARCHAR(5) NOT NULL,
	departure_date DATE NOT NULL,
	seat_number VARCHAR(10) NOT NULL,
	ticket_price DECIMAL(10,2) NOT NULL,
	CONSTRAINT FK_TICKET_PASSENGER FOREIGN KEY (passenger_id) REFERENCES Passengers (passenger_id),
	CONSTRAINT FK_TICKET_TRAIN FOREIGN KEY (train_id) REFERENCES Trains (train_id)
);

CREATE TABLE Transactions(
	transaction_id VARCHAR(5) PRIMARY KEY,
	ticket_id VARCHAR(5) NOT NULL,
	payment_method VARCHAR(50) NOT NULL,
	transaction_date DATE  NOT NULL,
	amount DECIMAL(10,2) NOT NULL,
	CONSTRAINT FK_Transactions_Tickets FOREIGN KEY (ticket_id) REFERENCES Tickets (ticket_id)
);


INSERT INTO Passengers(passenger_id, passenger_full_name, passenger_email, passenger_phone, passenger_cccd) 
VALUES
('P001', 'Nguyen Van An' ,'an.nguyen@example.com','0912345678' ,'001234567890'),
('P002','Tran Thi Binh', 'binh.tran@example.com', '0923456789', '002345678901'),
('P003', 'Le Minh Chau', 'chau.le@example.com', '0934567890', '003456789012'),
('P004', 'Pham Quoc Dat', 'dat.pham@example.com', '0945678901','004567890123'),
('P005','Vo Thanh Em', 'em.vo@example.com', '0956789012', '005678901234')

INSERT INTO Trains (train_id, train_name, train_type, total_seats)
VALUES ('T001', 'Tau Thong Nhat 1', 'SE', 500),
('T002', 'Tau Thong Nhat 2', 'TN', 450),
('T003', 'Tau Sai Gon - Hue', 'SE', 400),
('T004','Tau Ha Noi - Lao Cai', 'TN', 350),
('T005', 'Tau Da Nang Express', 'SE',300)

INSERT INTO Tickets (ticket_id, passenger_id, train_id, departure_date, seat_number, ticket_price)
VALUES 
('TK001', 'P001','T001','2025-06-10','A01',850000),
('TK002', 'P002', 'T002','2025-06-11', 'B05', 650000),
('TK003', 'P003', 'T003', '2025-06-12', 'C10', 720000),
('TK004', 'P004', 'T004', '2025-06-13', 'D12', 500000),
('TK005', 'P005', 'T005', '2025-06-14', 'E08', 900000)


INSERT INTO Transactions (transaction_id, ticket_id, payment_method, transaction_date, amount)
VALUES
('TR001', 'TK001', 'Credit Card', '2025-06-01', 850000),
('TR002', 'TK002', 'Cash', '2025-06-02', 650000),
('TR003', 'TK003', 'Bank Transfer', '2025-06-03', 720000),
('TR004', 'TK004', 'E-Wallet', '2025-06-04', 500000),
('TR005', 'TK005', 'Credit Card', '2025-06-05',900000)

-------------

-- 3. Cập nhật dữ liệu (6 điểm) Viết câu lệnh UPDATE giảm giá vé 15% (price = price * 0.85) 
-- 	cho các vé tàu có ngày khởi hành trước ngày 2025-05-01.

UPDATE Tickets SET ticket_price = ticket_price * 0.85 where departure_date < '2025-05-01'

-- 4. Xóa dữ liệu (6 điểm) Viết câu lệnh DELETE xóa các giao dịch có phương thức là "E-Wallet" 
-- và số tiền nhỏ hơn 200.000 VNĐ.
DELETE FROM Transactions where payment_method = 'E-Wallet' and amount < 200000

---------------
-- PHẦN 2: Truy vấn dữ liệu

-- 5. (3 điểm) Lấy thông tin hành khách gồm: mã HK, họ tên, email, SĐT sắp xếp theo họ tên giảm dần
SELECT p.passenger_id, p.passenger_full_name, p.passenger_email, p.passenger_phone
FROM passengers  p
ORDER BY p.passenger_full_name DESC

-- 6. (3 điểm) Lấy danh sách đoàn tàu gồm: mã tàu, tên tàu, tổng số ghế, sắp xếp theo số ghế tăng dần.
SELECT t.train_id, t.train_name, t.total_seats
FROM trains  t
ORDER BY t.total_seats ASC

-- 7. (3 điểm) Lấy thông tin vé đã đặt gồm: Họ tên hành khách, Tên tàu, Ngày khởi hành, Số ghế.
SELECT p.passenger_full_name, tr.train_name, t.departure_date, t.seat_number
FROM tickets t
JOIN Passengers p ON t.passenger_id = p.passenger_id
JOIN trains tr ON t.train_id = tr.train_id


-- 8. (3 điểm) Lấy danh sách hành khách và tổng tiền đã thanh toán: mã HK, họ tên, phương thức thanh toán, số tiền thanh toán, sắp xếp theo số tiền tăng dần.
	SELECT 
	FROM  transactions tr, (SELECT t.passenger_id
	FROM tickets t JOIN)
	JOIN tickets t ON t.ticket_id = tr.ticket_id
	ORDER BY

-- 9. (3 điểm) Lấy thông tin hành khách từ vị trí thứ 3 đến thứ 5 trong bảng Passengers sắp xếp theo tên (Z-A).
	SELECT * 
	FROM passengers p
	ORDER BY p.passenger_full_name
	DESC LIMIT 3 OFFSET 2 


-- 10. (5 điểm) Liệt kê các hành khách đã đặt ít nhất 3 vé tàu.
	SELECT p.passenger_full_name, p.passenger_email, p.passenger_phone
	FROM passengers p, 
	(SELECT t.passenger_id AS pass_id, COUNT(t.passenger_id) AS sl FROM tickets t GROUP BY passenger_id) AS sub
	WHERE 
	p.passenger_id = sub.pass_id AND sub.sl = 3

-- 11. (5 điểm) Liệt kê các đoàn tàu đã có hơn 10 lượt khách đặt vé.
	SELECT t.train_id, t.train_name
	FROM trains t, 
	(SELECT t.train_id AS train_idd,  COUNT(t.train_id) AS sl FROM tickets t GROUP BY train_id ) AS sub
	WHERE t.train_id = sub.train_idd AND sub.sl >10;


-- 12. (5 điểm) Lấy danh sách hành khách có tổng tiền giao dịch > 2.000.000 VNĐ, gồm: mã HK, họ tên, mã tàu, tổng tiền.

	SELECT p.passenger_id ,p.passenger_full_name, t.train_id , SUM(tr.amount) AS Tong
	FROM transactions tr
	JOIN tickets t ON t.ticket_id = tr.ticket_id
	JOIN passengers p on t.passenger_id = p.passenger_id
	WHERE Tong > 2000000
	GROUP BY p.passenger_id
	
	


-- 13. (6 điểm) Lấy danh sách hành khách có tên chứa chữ "Hoàng" hoặc địa chỉ email thuộc miền "@gmail.com". Sắp xếp theo tên tăng dần.
	SELECT * FROM passengers p WHERE p.passenger_full_name LIKE '%Hoàng%' OR p.passenger_email LIKE '%@gmail.com'


-- 14. (4 điểm)  Lấy danh sách đoàn tàu (trang thứ 2, mỗi trang 5 bản ghi) sắp xếp theo số ghế giảm dần.
	SELECT * FROM trains t ORDER BY t.total_seats DESC LIMIT 5 OFFSET 5

--------------------

-- PHẦN 3: Tạo View
-- 15. (5 điểm) Tạo view vw_UpcomingTrips hiển thị thông tin tàu và hành khách đã đặt vé 
-- với ngày khởi hành sau ngày 2025-06-01, gồm: Họ tên, Tên tàu, Số ghế, Giá vé, Ngày khởi hành.

	CREATE VIEW vw_UpcomingTrips AS 
		SELECT p.passenger_full_name, tr.train_name, t.seat_number, t.departure_date
		FROM tickets t 
		JOIN Passengers p ON t.passenger_id = p.passenger_id
		JOIN trains tr ON t.train_id = tr.train_id
		WHERE t.departure_date > '2025-06-01'


-- 16. (5 điểm) Tạo view vw_HighValueTickets hiển thị khách hàng đặt vé có giá trị trên 500.000 VNĐ, 
-- gồm: Họ tên, Tên tàu, Số ghế, Giá vé.

CREATE VIEW vw_HighValueTickets AS
	SELECT p.passenger_full_name, tr.train_name, t.seat_number, t.ticket_price
	FROM tickets t 
	JOIN Passengers p ON t.passenger_id = p.passenger_id
	JOIN trains tr ON t.train_id = tr.train_id
	WHERE t.ticket_price >500000

-- PHẦN 4: Tạo Trigger
-- 17. (5 điểm) Tạo trigger tg_check_ticket_date kiểm tra khi chèn vào bảng Tickets.
-- Nếu ngày khởi hành nhỏ hơn ngày hiện tại thì báo lỗi "Ngày khởi hành không hợp lệ" và hủy thao tác.

-- Tạo function check date
	CREATE OR REPLACE FUNCTION fn_check_date()
	RETURNS TRIGGER AS $$
	DECLARE 
		v_departure_date date;
		v_current_date date;
	BEGIN
		SELECT NEW.departure_date into v_departure_date;
		v_current_date = NOW();
		
		IF  ( v_departure_date < v_current_date )THEN 
			RAISE EXCEPTION 'Ngày suất phát không được là ngày trong quá khứ';
		END IF;
		RETURN NEW;
	END;
	$$ LANGUAGE plpgsql;

-- Tạo trigger
	create trigger tg_check_ticket_date
	BEFORE INSERT ON tickets
	FOR EACH ROW EXECUTE FUNCTION fn_check_date();
	


-- 18. (5 điểm) Tạo trigger tg_update_seats tự động giảm total_seats của bảng Trains 
-- đi 1 khi có một bản ghi mới được thêm vào bảng Tickets.

	--Tạo function update seat
	CREATE OR REPLACE FUNCTION fn_update_seats()
		RETURNS TRIGGER AS $$
		BEGIN
			UPDATE trains t SET total_seats = total_seats - 1 WHERE train_id = NEW.train_id;
			RETURN NEW;
		END;
		$$ LANGUAGE plpgsql;
	

	-- Tạo trigger update seat
	create trigger tg_update_seats
	AFTER INSERT ON tickets
	FOR EACH ROW EXECUTE FUNCTION fn_update_seats();


-- Thử Trigger
-- INSERT INTO Tickets (ticket_id, passenger_id, train_id, departure_date, seat_number, ticket_price)
-- values 
-- ('TK006', 'P001','T001','2026-06-10','A01',850000)
-- SELECT * FROM trains
	

-- PHẦN 5: Tạo Store Procedure

-- 19. (5 điểm) Viết Procedure sp_add_passenger để thêm mới một hành khách.

	CREATE OR REPLACE PROCEDURE sp_add_passenger (
	p_passenger_id VARCHAR(5),
	p_passenger_full_name VARCHAR(100), 
	p_passenger_email VARCHAR(100), 
	p_passenger_phone VARCHAR(15), 
	p_passenger_cccd VARCHAR(20))
	LANGUAGE plpgsql AS $$
	BEGIN
		INSERT INTO Passengers(passenger_id, passenger_full_name, passenger_email, passenger_phone, passenger_cccd) 
		values (p_passenger_id, p_passenger_full_name, p_passenger_email,p_passenger_phone, p_passenger_cccd);
	END;
	$$

-- CALL sp_add_passenger('P006','PHAM PHAT','Phat@gmail','09298475737','2948282834885')
-- SELECT * FROM passengers

-- 20. (5 điểm) Viết Procedure sp_cancel_ticket nhận vào p_ticket_id,
-- thực hiện xóa vé trong bảng Tickets và các giao dịch liên quan trong bảng Transactions.


CREATE OR REPLACE PROCEDURE sp_cancel_ticket ( p_ticket_id VARCHAR(5))
LANGUAGE plpgsql AS $$
	BEGIN
	DELETE FROM transactions where ticket_id = p_ticket_id;
	DELETE FROM tickets where ticket_id = p_ticket_id;
	END;
$$

-- CALL sp_cancel_ticket('TK001');
-- SELECT * from tickets



