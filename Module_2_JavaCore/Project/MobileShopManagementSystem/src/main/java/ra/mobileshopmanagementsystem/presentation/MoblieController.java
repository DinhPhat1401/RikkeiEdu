package ra.mobileshopmanagementsystem.presentation;

import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MoblieController {
    CustomUtil customUtil = new CustomUtil();
    public int showMenu() {
        System.out.println("1. Quản lý khách hàng");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Quản lý đơn hàng");
        System.out.println("4. Thoát");
        int choice = customUtil.getInt("Vui lòng chọn chức năng: ");
        return choice;
    }
    public int showMenuManageCustomer() {
        System.out.println("Quản lý khách hàng");
        System.out.println("1. Thêm mới khách hàng");
        System.out.println("2. Cập nhật thông tin khách hàng");
        System.out.println("3. Xóa khách hàng");
        System.out.println("4. Hiển thị danh sách khách hàng");
        System.out.println("5. Quay lại menu chính");
        int choice = customUtil.getInt("Vui lòng chọn chức năng: ");
        return choice;
    }
    public int showMenuManageProduct() {
        System.out.println("Quản lý điện thoại");
        System.out.println("1. Thêm mới điện thoại");
        System.out.println("2. Cập nhật thông tin điện thoại");
        System.out.println("3. Xóa điện thoại");
        System.out.println("4. Hiển thị danh sách điện thoại");
        System.out.println("5. Tìm kiếm điện thoại theo brand");
        System.out.println("6. Tìm kiếm điện thoại trong khoảng giá");
        int choice = customUtil.getInt("Vui lòng chọn chức năng: ");
        return choice;
    }

    public void login() {
        while (true) {
            String email = customUtil.getString("Nhập email: ");
            String password = customUtil.getString("Nhập mật khẩu: ");
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

    public void displayListCustomer(List<?> customers) {
        if (customers.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (Object customer : customers) {
                System.out.println(customer);
            }
        }

    }
}
