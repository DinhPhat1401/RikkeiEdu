package ra.mobileshopmanagementsystem.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomUtil {
    Scanner scanner = new Scanner(System.in);
    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    public int getInt(String message) {
        while(true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
    public void login() {
        while (true) {
            String email = getString("Nhập email: ");
            String password = getString("Nhập mật khẩu: ");
            PreparedStatement ps = null;
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                ps = conn.prepareStatement("SELECT * FROM customer WHERE email = ? AND password = ? AND role = 'ADMIN'");
                ps.setString(1, email);
                ps.setString(2, password);
                if (ps.executeQuery().next()) {
                    System.out.println("Đăng nhập thành công!");
                    break;
                } else {
                    System.out.println("Đăng nhập thất bại! Vui lòng kiểm tra email, mật khẩu và thử lại.");

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}
