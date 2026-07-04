package ra.mobileshopmanagementsystem.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomUtil {
    Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        while (true) {
            message = scanner.nextLine();
            if (message.isBlank()) {
                System.out.println("Vui lòng nhập dữ liệu! ");
            } else {
                return message;
            }
        }
    }

    public int getInt(String message) {
        System.out.print(message);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập một số hợp lệ");
            }
        }
    }

    public String getEmail(String message) {
        System.out.print(message);
        while (true) {
            message = scanner.nextLine();
            if (message.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return message;
            } else {
                System.out.println("Vui lòng nhập một email hợp lệ");
            }
        }
    }

    public String getPassword(String message) {
        System.out.print(message);
        while (true) {
            message = scanner.nextLine();
            if (message.isBlank()) {
                System.out.println("Mật khẩu không được để trống");
            } else if (message.length() < 8) {
                System.out.println("Mật khẩu phải có ít nhất 8 ký tự");
            } else {
                return BCrypt.hashpw(message, BCrypt.gensalt());
            }
        }
    }

    public String getPhoneNumber(String message){
        System.out.print(message);
        while (true){
            message = scanner.nextLine();
            if (message.matches("^(\\+84|0)\\d{9}$")){
                return message;
            } else {
                System.out.println("Vui lòng nhập số điện thoại hợp lệ (10 số, bắt đầu bằng +84 hoặc 0)");
            }

        }
    }

    public boolean checkPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }

    public double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    public LocalDate getLocalDate(String message) {
        System.out.println(message);
        while (true) {
            try {
                String date = scanner.nextLine();
                return LocalDate.parse(date);
            } catch (Exception e) {
                System.out.println("Vui lòng nhập ngày hợp lệ theo định dạng yyyy-MM-dd");
            }
        }
    }
}
