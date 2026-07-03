package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.dao.IPhone;
import ra.mobileshopmanagementsystem.model.Product;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PhoneDaoImpl implements IPhone {
    CustomUtil customUtil = new CustomUtil();
    @Override
    public boolean addPhone() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement("INSERT INTO product (name, brand, price, stock) VALUES (?, ?, ?, ?)");
            ps.setString(1, customUtil.getString("Nhập tên sản phẩm: "));
            ps.setString(2, customUtil.getString("Nhập thương hiệu: "));
            ps.setDouble(3, customUtil.getDouble("Nhập giá: "));
            ps.setInt(4, customUtil.getInt("Nhập số lượng tồn kho: "));
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
        return false;
    }

    @Override
    public boolean deletePhone() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            int phoneId = -1;
            while (true) {
                phoneId = customUtil.getInt("Nhập ID sản phẩm cần xóa: ");
                Product existing = getPhoneById(phoneId);
                if (existing != null) {
                    String confirm = customUtil.getString("Bạn có chắc chắn muốn xóa sản phẩm này không? (Nhập 'yes' để xác nhận): ");
                    if (confirm.equalsIgnoreCase("yes")) {
                        ps = connection.prepareStatement("DELETE FROM product WHERE id = ?");
                        ps.setInt(1, phoneId);
                        int rowsAffected = ps.executeUpdate();
                        return rowsAffected > 0;
                    }
                    return false;
                }
                    System.out.println("Sản phẩm với ID " + phoneId + " không tồn tại. Vui lòng thử lại.");
            }
        } catch (Exception e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
        return false;
    }


    @Override
    public boolean updatePhone() {
        return false;
    }

    @Override
    public List<Product> getAllPhone() {
        return List.of();
    }

    @Override
    public Product getPhoneById(int id) {
        return null;
    }

    @Override
    public List<Product> getPhoneByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getPhoneByNameAndAvailabilityStock(String name) {
        return List.of();
    }
}
