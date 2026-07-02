package ra.mobileshopmanagementsystem;

import org.postgresql.util.PSQLException;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
//    static {
//        try {
//            Connection conn = DBUtil.getConnection();
//            PreparedStatement ps = conn.prepareStatement("INSERT INTO customer (name, phone, email, password, address, role) VALUES (?, ?, ?, ?, ?, 'ADMIN')");
//            ps.setString(1, "admin");
//            ps.setString(2, "0123456789");
//            ps.setString(3, "admin@admin");
//            ps.setString(4, "12345");
//            ps.setString(5, "Ha Noi");
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected == 0) {
//                System.out.println("Have problem, may admin user already exists.");
//            }
//        } catch (SQLException e) {
//            String sqlState = e.getSQLState();
//           if (sqlState.equals("23505")) {
//                System.out.println("Admin user already exists.");
//            } else {
//                e.printStackTrace();
//            }
//        } finally {
//            try {
//                Connection conn = DBUtil.getConnection();
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) {
        CustomUtil customUtil = new CustomUtil();
        System.out.println("Chào mừng bạn đến với hệ thống quản lý cửa hàng điện thoại");
        System.out.println("Bạn cần đăng nhập với tư cách là ADMIN để truy cập vào hệ thống!");
        customUtil.login();
        while (true) {
            System.out.println("Chào mừng admin đến với hệ thống quản lý cửa hàng điện thoại");


            switch (choice) {
                case 1:
                    // Quản lý khách hàng
                    Cus customerManager = new CustomerManager();
                    customerManager.showMenuManageCustomer();
                    break;
            }
        }
    }
}