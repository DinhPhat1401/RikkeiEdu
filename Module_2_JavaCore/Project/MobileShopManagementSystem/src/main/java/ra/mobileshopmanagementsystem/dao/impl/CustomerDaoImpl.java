package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.dao.ICustomer;
import ra.mobileshopmanagementsystem.enums.Role;
import ra.mobileshopmanagementsystem.model.Customer;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.*;
import java.util.List;

import ra.mobileshopmanagementsystem.utils.CustomUtil;

public class CustomerDaoImpl implements ICustomer {
    CustomUtil customUtil = new CustomUtil();




    @Override
    public boolean addCustomer() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement("INSERT INTO customer (name, phone, email, password, address, role) VALUES (?, ?, ?, ?, ?, 'CUSTOMER')");
            ps.setString(1, customUtil.getString("Nhập tên khách hàng: "));
            ps.setString(2, customUtil.getString("Nhập số điện thoại: "));
            ps.setString(3, customUtil.getString("Nhập email: "));
            ps.setString(4, customUtil.getString("Nhập mật khẩu: "));
            ps.setString(5, customUtil.getString("Nhập địa chỉ: "));
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
    public boolean deleteCustomer() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            int customerId = -1;
            while (true) {
                customerId = customUtil.getInt("Nhập ID khách hàng cần xóa: ");
                Customer existing = getCustomerById(customerId);
                if (existing != null) {
                    String confirm =  customUtil.getString("Bạn có chắc chắn muốn xóa khách hàng này không? (Nhập 'yes' để xác nhận): ");
                    if (confirm.equalsIgnoreCase("yes")) {
                        ps = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
                        ps.setInt(1, customerId);
                        int rowsAffected = ps.executeUpdate();
                        return rowsAffected > 0;
                    }
                    return false;
                }
                System.out.println("Không tìm thấy khách hàng với ID: " + customerId + ". Vui lòng thử lại.");
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
    public boolean updateCustomer() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            int customerId = -1;
            while (true) {
                customerId = customUtil.getInt("Nhập ID khách hàng cần cập nhật thông tin: ");
                Customer existing = getCustomerById(customerId);
                if (existing != null) {
                    ps = connection.prepareStatement("UPDATE customer SET name = ?, phone = ?, email = ?, password = ?, address = ? WHERE id = ?");
                    ps.setString(1, customUtil.getString("Nhập tên khách hàng: "));
                    ps.setString(2, customUtil.getString("Nhập số điện thoại: "));
                    ps.setString(3, customUtil.getString("Nhập email: "));
                    ps.setString(4, customUtil.getString("Nhập mật khẩu: "));
                    ps.setString(5, customUtil.getString("Nhập địa chỉ: "));
                    ps.setInt(6, customerId);
                    int rowsAffected = ps.executeUpdate();
                    return rowsAffected > 0;
                }
                System.out.println("Không tìm thấy khách hàng với ID: " + customerId + ". Vui lòng thử lại.");
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
    public List<Customer> getAllCustomer() {
        List<Customer> customers = null;
        Connection connection = null;
        Statement stm = null;
        Customer customer = null;
        try {
            connection = DBUtil.getConnection();
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getString("address"));
                customer.setRole(Role.valueOf(rs.getString("role")));
                customers.add(customer);
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
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        Customer customer = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM customer where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getString("address"));
                customer.setRole(Role.valueOf(rs.getString("role")));
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
        return customer;
    }
}
