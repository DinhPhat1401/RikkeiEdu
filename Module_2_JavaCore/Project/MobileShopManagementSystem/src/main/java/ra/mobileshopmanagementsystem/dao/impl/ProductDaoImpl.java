package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.business.impl.ProductBusinessImpl;
import ra.mobileshopmanagementsystem.dao.IProductDao;
import ra.mobileshopmanagementsystem.model.Product;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    CustomUtil customUtil = new CustomUtil();
    public boolean existsById(Connection connection, int id) throws SQLException {
        String sql = "SELECT 1 FROM product WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
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

                phoneId = customUtil.getInt("Nhập ID sản phẩm cần xóa: ");
                if (existsById(connection, phoneId)) {
                    String confirm = customUtil.getString("Bạn có chắc chắn muốn xóa sản phẩm này không (Y/n):  ");
                    if (confirm.equalsIgnoreCase("y")) {
                        ps = connection.prepareStatement("DELETE FROM product WHERE id = ?");
                        ps.setInt(1, phoneId);
                        int rowsAffected = ps.executeUpdate();
                        return rowsAffected > 0;
                    }
                    return false;
                }
                    System.out.println("Sản phẩm với ID " + phoneId + " không tồn tại. Vui lòng thử lại.");

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
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            int productId = -1;
            while (true) {
                productId = customUtil.getInt("Nhập ID sản phẩm cần cập nhật thông tin: ");

                if (existsById(connection,productId)) {
                    ps = connection.prepareStatement("UPDATE product SET name = ?, brand = ?, price = ?, stock = ? WHERE id = ?");
                    ps.setString(1, customUtil.getString("Nhập tên sản phẩm: "));
                    ps.setString(2, customUtil.getString("Nhập thương hiệu: "));
                    ps.setDouble(3, customUtil.getDouble("Nhập giá: "));
                    ps.setInt(4, customUtil.getInt("Nhập số lượng tồn kho: "));
                    ps.setInt(5, productId);
                    int rowsAffected = ps.executeUpdate();
                    return rowsAffected > 0;
                }
                System.out.println("Không tìm thấy sản phẩm với ID: " + productId + ". Vui lòng thử lại.");
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
    public List<Product> getAllPhone() {
        List<Product> phones = null;
        Connection connection = null;
        Statement stm = null;
        try {
            connection = DBUtil.getConnection();
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM product");
            phones = new java.util.ArrayList<>();
            while (rs.next()) {
                Product phone = new Product();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getDouble("price"));
                phone.setStock(rs.getInt("stock"));
                phones.add(phone);
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
        return phones;
    }

}
