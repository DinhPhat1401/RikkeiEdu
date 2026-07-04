package ra.mobileshopmanagementsystem.presentation;

import org.mindrot.jbcrypt.BCrypt;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        System.out.println("7. Tìm kiếm điện thoại theo tên với điều kiện tồn kho > 0");
        System.out.println("8. Quay lại menu chính");
        return customUtil.getInt("Vui lòng chọn chức năng: ");
    }
    public int showMenuManageOrder() {
        System.out.println("Quản lý đơn hàng");
        System.out.println("1. Thêm mới đơn hàng");
        System.out.println("2. Hiện thị danh sách đơn hàng");
        System.out.println("3. Tìm kiếm hóa đơn");
        System.out.println("4. Thống kê doanh thu");
        System.out.println("5. Quay lại menu chính");
        return customUtil.getInt("Vui lòng chọn chức năng: ");
    }
    public int showSubMenuSearchInvoice() {
        System.out.println("Bạn muốn tìm kiếm hóa đơn theo:");
        System.out.println("1. Tên khách hàng");
        System.out.println("2. Ngày tháng năm xuất hóa đơn");
        System.out.println("3. Quay lại quản lý hóa đơn");
        return customUtil.getInt("Vui lòng chọn chức năng: ");
    }
    public int showSubMenuRevenueStatistics() {
        System.out.println("Bạn muốn thống kê doanh thu theo:");
        System.out.println("1. Ngày");
        System.out.println("2. Tháng");
        System.out.println("3. Năm");
        System.out.println("4. Quay lại menu thống kê doanh thu");
        return customUtil.getInt("Vui lòng chọn chức năng: ");
    }



    public void login() {
        while (true) {
            String email = customUtil.getEmail("Nhập email: ");
            String password = customUtil.getString("Nhập mật khẩu: ");
            PreparedStatement ps = null;
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                ps = conn.prepareStatement("SELECT * FROM customer WHERE email = ?  AND role = 'ADMIN'");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("Email không tồn tại hoặc không phải tài khoản ADMIN.");
                } else {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        System.out.println("Đăng nhập thành công!");
                        break;
                    } else {
                        System.out.println("Sai mật khẩu!");
                    }
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
        if (customers == null || customers.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (Object customer : customers) {
                System.out.println(customer);
            }
        }
    }
    public void displayListPhone(List<?> phones) {
        if (phones.isEmpty()) {
            System.out.println("Danh sách điện thoại trống.");
        } else {
            System.out.println("Danh sách điện thoại:");
            for (Object phone : phones) {
                System.out.println(phone);
            }
        }
    }


}
