package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.dao.IInvoice;
import ra.mobileshopmanagementsystem.model.Invoice;
import ra.mobileshopmanagementsystem.model.InvoiceDetail;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

public class InvoiceDaoImpl implements IInvoice {
    private CustomUtil customUtil = new CustomUtil();

    boolean existsById(Connection connection, int id) throws SQLException {
        String sql = "SELECT 1 FROM invoice WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    boolean existsByCustomerId(Connection connection, int customerId) throws SQLException {
        String sql = "SELECT 1 FROM customer WHERE customer_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public boolean addInvoice() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBUtil.getConnection();
            int customerId = -1;
            while (true) {
                customerId = customUtil.getInt("Nhập ID khách hàng: ");
                if (existsByCustomerId(connection, customerId)) {
                    break;
                } else {
                    System.out.println("ID khách hàng không tồn tại. Vui lòng nhập lại.");
                }
            }
            ps = connection.prepareStatement("INSERT INTO invoice (customer_id, created_at, total_price) VALUES (?, ?, ?)");
            ps.setInt(1, customerId);
            OffsetDateTime currentTimestamp = OffsetDateTime.now();
            ps.setObject(2, currentTimestamp);
            ps.setDouble(3, 0.0);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean addInvoiceDetail() {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            int invoiceId = -1;
            while (true) {
                invoiceId = customUtil.getInt("Nhập ID hóa đơn: ");
                if (existsById(connection, invoiceId)) {
                    break;
                } else {
                    System.out.println("ID hóa đơn không tồn tại. Vui lòng nhập lại.");
                }
            }
            int productId = -1;
            while (true) {
                productId = customUtil.getInt("Nhập ID sản phẩm: ");
                if (existsById(connection, productId)) {
                    break;
                } else {
                    System.out.println("ID sản phẩm không tồn tại. Vui lòng nhập lại.");
                }
            }
            int quantity = customUtil.getInt("Nhập số lượng: ");
            double unitPrice = customUtil.getDouble("Nhập giá đơn vị: ");

            try {
                PreparedStatement ps1 = null;
                ps1 = connection.prepareStatement("INSERT INTO invoice_detail (invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)");
                ps1.setInt(1, invoiceId);
                ps1.setInt(2, productId);
                ps1.setInt(3, quantity);
                ps1.setDouble(4, unitPrice);
                ps1.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage());
                connection.rollback();
                return false;
            }

            try {
                PreparedStatement ps2 = null;
                ps2 = connection.prepareStatement("UPDATE invoice SET total_price = ? WHERE id = ?");
                ps2.setDouble(1, unitPrice * quantity);
                ps2.setInt(2, invoiceId);
                ps2.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage());
                connection.rollback();
                return false;
            }

            try {
                PreparedStatement ps3 = null;
                ps3 = connection.prepareStatement("UPDATE product SET stock = stock - ? WHERE id = ?");
                ps3.setInt(1, quantity);
                ps3.setInt(2, productId);
                ps3.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Lỗi khi cập nhật số lượng tồn kho: " + e.getMessage());
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean deleteInvoice() {
        return false;
    }

    @Override
    public boolean updateInvoice() {
        return false;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return List.of();
    }

    @Override
    public List<InvoiceDetail> getAllInvoiceDetail(int invoiceId) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        List<InvoiceDetail> invoiceDetails = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM invoice_detail WHERE invoice_id = ?");
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoiceId(rs.getInt("invoice_id"));
                invoiceDetail.setProductId(rs.getInt("product_id"));
                invoiceDetail.setQuantity(rs.getInt("quantity"));
                invoiceDetail.setUnitPrice(rs.getDouble("unit_price"));
                invoiceDetails.add(invoiceDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceDetails;
    }

}
