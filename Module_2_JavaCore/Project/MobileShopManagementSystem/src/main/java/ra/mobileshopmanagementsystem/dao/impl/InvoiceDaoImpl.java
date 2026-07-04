package ra.mobileshopmanagementsystem.dao.impl;

import ra.mobileshopmanagementsystem.dao.IInvoice;
import ra.mobileshopmanagementsystem.model.Invoice;
import ra.mobileshopmanagementsystem.model.InvoiceDetail;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements IInvoice {
    private CustomUtil customUtil = new CustomUtil();
    private ProductDaoImpl productDao = new ProductDaoImpl();

    private boolean existsById(Connection connection, int id) throws SQLException {
        String sql = "SELECT 1 FROM invoice WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    private boolean existsProductById(Connection connection, int id) throws SQLException {
        String sql = "SELECT 1 FROM product WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private boolean existsByCustomerId(Connection connection, int customerId) throws SQLException {
        String sql = "SELECT 1 FROM customer WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private int getStock(Connection connection, int productId) throws SQLException {
        String sql = "SELECT stock FROM product WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        }
        return 0;
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
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO invoice (customer_id, created_at, total_amount) VALUES (?, ?, ?)"
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerId);
            OffsetDateTime currentTimestamp = OffsetDateTime.now();
            ps.setObject(2, currentTimestamp);
            ps.setDouble(3, 0.0);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                connection.commit();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if(generatedKeys.next() && addInvoiceDetail(connection,generatedKeys.getInt(1))){
                    System.out.println("Thêm chi tiết đơn hàng thành công!");
                    connection.commit();
                    return true;
                } else {
                    System.out.println("Thêm chi tiết đơn hàng thất bại!");
                }
            }
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
    public boolean addInvoiceDetail(Connection connection,int invoiceId) {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            int productId = -1;
            while (true) {
                productId = customUtil.getInt("Nhập ID sản phẩm: ");
                if (existsProductById(connection, productId)) {
                    break;
                } else {
                    System.out.println("ID sản phẩm không tồn tại. Vui lòng nhập lại.");
                }
            }
            int quantity = 0;
            while (true) {
                quantity = customUtil.getInt("Nhập số lượng: ");
                int stock = getStock(connection, productId);
                if (quantity > stock) {
                    System.out.println("Số lượng vượt quá số lượng tồn kho. Vui lòng nhập lại.");
                    continue;
                }
                if (stock == 0) {
                    System.out.println("Sản phẩm đã hết hàng. Vui lòng chọn sản phẩm khác.");
                    return false;
                }
                if (quantity <= 0) {
                    System.out.println("Số lượng phải lớn hơn 0. Vui lòng nhập lại.");
                    continue;
                }

                break;
            }

            double unitPrice = productDao.getPhoneById(productId).getPrice();
//            System.err.println("Đơn giá sản phẩm: " + unitPrice);
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

            try {
                PreparedStatement ps1 = null;
                ps1 = connection.prepareStatement("INSERT INTO invoice_details (invoice_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)");
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
                ps2 = connection.prepareStatement("UPDATE invoice SET total_amount = ? WHERE id = ?");
                ps2.setDouble(1, unitPrice * quantity);
                ps2.setInt(2, invoiceId);
                ps2.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Lỗi khi thêm chi tiết hóa đơn: " + e.getMessage());
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
    public List<Invoice> getAllInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        Connection connection = null;
        Invoice invoice = null;
        Statement stm = null;
        try {
            connection = DBUtil.getConnection();
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM invoice");
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class).toLocalDateTime());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoices.add(invoice);
            }

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
        return invoices;

    }

    @Override
    public List<InvoiceDetail> getAllInvoiceDetail(int invoiceId) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM invoice_details WHERE invoice_id = ?");
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
//            System.out.println("Lỗi khi lấy chi tiết hóa đơn: " + e.getMessage());
            System.out.println("Chưa có chi tiết đơn hàng");
        }
        return invoiceDetails;
    }

    @Override
    public void showAllInvoice() {
            List<Invoice> invoices = getAllInvoice();
            if (invoices == null || invoices.isEmpty()) {
                System.out.println("Danh sách hóa đơn trống.");
                return;
            }
            System.out.println("Danh sách hóa đơn:");
            for (Invoice invoice : invoices) {
                System.out.println(invoice);
                List<InvoiceDetail> invoiceDetails = getAllInvoiceDetail(invoice.getId());
                for (InvoiceDetail invoiceDetail : invoiceDetails) {
                    System.out.println(invoiceDetail);
                }
            }
    }
}
