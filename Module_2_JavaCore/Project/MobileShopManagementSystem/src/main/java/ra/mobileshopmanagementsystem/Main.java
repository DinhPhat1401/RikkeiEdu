package ra.mobileshopmanagementsystem;

import org.mindrot.jbcrypt.BCrypt;
import org.postgresql.util.PSQLException;
import ra.mobileshopmanagementsystem.business.impl.InvoiceBusinessImpl;
import ra.mobileshopmanagementsystem.business.impl.ProductBusinessImpl;
import ra.mobileshopmanagementsystem.dao.impl.CustomerDaoImpl;
import ra.mobileshopmanagementsystem.dao.impl.InvoiceDaoImpl;
import ra.mobileshopmanagementsystem.dao.impl.ProductDaoImpl;
import ra.mobileshopmanagementsystem.presentation.MoblieController;
import ra.mobileshopmanagementsystem.utils.CustomUtil;
import ra.mobileshopmanagementsystem.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    static {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO customer (name, phone, email, password, address, role) VALUES (?, ?, ?, ?, ?, 'ADMIN')");
            ps.setString(1, "admin");
            ps.setString(2, "0123456789");
            ps.setString(3, "admin@admin.com");
            ps.setString(4, BCrypt.hashpw("admin123", BCrypt.gensalt()));
            ps.setString(5, "Ha Noi");
            ps.executeUpdate();
        } catch (SQLException e) {
            String sqlState = e.getSQLState();
            if (sqlState.equals("23505")) {
//                System.out.println("Admin user already exists.");
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                Connection conn = DBUtil.getConnection();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean existsById(Connection connection, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM your_table_name WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery().next();
        }
    }

    public static void main(String[] args) {
        MoblieController moblieController = new MoblieController();
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        CustomUtil customUtil = new CustomUtil();
        ProductDaoImpl productDao = new ProductDaoImpl();
        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        InvoiceBusinessImpl invoiceBusiness = new InvoiceBusinessImpl();
        ProductBusinessImpl productBusiness = new ProductBusinessImpl();
        boolean isContinue = true;
        System.out.println("Chào mừng bạn đến với hệ thống quản lý cửa hàng điện thoại");
        System.out.println("Bạn cần đăng nhập với tư cách là ADMIN để truy cập vào hệ thống!");
        moblieController.login();
        while (true) {
            isContinue = true;
            System.out.println("Chào mừng admin đến với hệ thống quản lý cửa hàng điện thoại");
            switch (moblieController.showMenu()) {
                case 1:
                    while (isContinue) {
                        switch (moblieController.showMenuManageCustomer()) {
                            case 1:
                                if (customerDao.addCustomer()) {
                                    System.out.println("Thêm khách hàng thành công!");
                                } else {
                                    System.out.println("Thêm khách hàng thất bại!");
                                }
                                break;
                            case 2:
                                if (customerDao.updateCustomer()) {
                                    System.out.println("Cập nhật khách hàng thành công!");
                                } else {
                                    System.out.println("Cập nhật khách hàng thất bại!");
                                }
                                break;
                            case 3:
                                if (customerDao.deleteCustomer()) {
                                    System.out.println("Xóa khách hàng thành công!");
                                } else {
                                    System.out.println("Chưa có khách hàng nào được xóa!");
                                }
                                break;
                            case 4:
                                moblieController.displayListCustomer(customerDao.getAllCustomer());
                                break;
                            case 5:
                                isContinue = false;
                                break;
                            default:
                                System.out.println("Vui lòng chọn chức năng hợp lệ!");
                        }
                    }
                    break;
                case 2:
                    while (isContinue) {
                        switch (moblieController.showMenuManageProduct()) {
                            case 1:
                                if (productDao.addPhone()) {
                                    System.out.println("Thêm điện thoại thành công!");
                                } else {
                                    System.out.println("Thêm điện thoại thất bại!");
                                }
                                break;
                            case 2:
                                if (productDao.updatePhone()) {
                                    System.out.println("Cập nhật điện thoại thành công!");
                                } else {
                                    System.out.println("Cập nhật điện thoại thất bại!");
                                }
                                break;
                            case 3:
                                if (productDao.deletePhone()) {
                                    System.out.println("Xóa điện thoại thành công!");
                                } else {
                                    System.out.println("Chưa có điện thoại nào được xóa!");
                                }
                                break;
                            case 4:
                                moblieController.displayListPhone(productDao.getAllPhone());
                                break;
                            case 5:
                                String brand = customUtil.getString("Nhập thương hiệu điện thoại cần tìm kiếm: ");
                                moblieController.displayListPhone(productBusiness.getPhoneByBrand(brand));
                                break;
                            case 6:
                                double minPrice = customUtil.getDouble("Nhập giá thấp nhất: ");
                                double maxPrice = customUtil.getDouble("Nhập giá cao nhất: ");
                                if (minPrice > maxPrice) {
                                    System.out.println("Dường như bạn đã nhập nhầm giá thấp nhất và giá cao nhất rồi (min>max), tôi đã tự động đổi lại cho bạn!");
                                    double temp = minPrice;
                                    minPrice = maxPrice;
                                    maxPrice = temp;
                                }
                                moblieController.displayListPhone(productBusiness.getPhoneInRange(minPrice, maxPrice));
                                break;
                            case 7:
                                String name = customUtil.getString("Nhập tên điện thoại cần tìm kiếm: ");
                                moblieController.displayListPhone(productBusiness.getPhoneByNameAndAvailabilityStock(name));
                                break;
                            case 8:
                                isContinue = false;
                                break;
                            default:
                                System.out.println("Vui lòng chọn chức năng hợp lệ!");
                        }
                    }
                    break;
                case 3:
                    while (isContinue) {
                       switch (moblieController.showMenuManageOrder()){
                           case 1:
                               if (invoiceDao.addInvoice()) {
                                   System.out.println("Thêm đơn hàng thành công! Vui lòng thêm chi tiết đơn hàng");
                               } else {
                                   System.out.println("Thêm đơn hàng thất bại!");
                               }
                               break;
                           case 2:
                               invoiceDao.showAllInvoice();
                               break;
                           case 3:
                               boolean subMenuContinue = true;
                               while (subMenuContinue) {
                                   switch (moblieController.showSubMenuSearchInvoice()) {
                                       case 1:
                                           String customerName = customUtil.getString("Nhập tên khách hàng cần tìm kiếm: ");
                                           invoiceBusiness.getAllInvoiceByCustomerName(customerName).forEach(invoice -> System.out.println(invoice));
                                           break;
                                       case 2:
                                           LocalDate date = customUtil.getLocalDate("Nhập ngày tháng năm xuất hóa đơn (yyyy-MM-dd): ");
                                           invoiceBusiness.getAllInvoiceByDate(date).forEach(invoice -> System.out.println(invoice));
                                           break;
                                       case 3:
                                           subMenuContinue = false;
                                           break;
                                       default:
                                           System.out.println("Vui lòng chọn chức năng hợp lệ!");
                                   }
                               }
                               break;
                           case 4:
                               boolean subMenuRevenue = true;
                               while (subMenuRevenue) {
                                   switch (moblieController.showSubMenuRevenueStatistics()) {
                                       case 1:

                                               System.out.printf("Thống kê doanh thu theo ngày: %,.2f \n" , invoiceBusiness.getTotalAmountByDate(customUtil.getLocalDate("Nhập ngày cần thống kê doanh thu (yyyy-MM-dd): ")));

                                           break;
                                       case 2:

                                               System.out.printf("Thống nghê doanh thu theo tháng: %,.2f \n" , invoiceBusiness.getTotalAmountByMonth(customUtil.getInt("Nhập tháng cần thống kê doanh thu (1-12): "), customUtil.getInt("Nhập năm cần thống kê doanh thu: ")));

                                           break;
                                       case 3:

                                               System.out.printf("Thống kê doanh thu theo năm: %,.2f \n" , invoiceBusiness.getTotalAmountByYear(customUtil.getInt("Nhập năm cần thống kê doanh thu: ")));

                                           break;
                                       case 4:
                                           subMenuRevenue = false;
                                           break;
                                       default:
                                           System.out.println("Vui lòng chọn chức năng hợp lệ!");
                                   }
                               }
                               break;
                           case 5:
                               isContinue = false;
                               break;
                           default:
                               System.out.println("Vui lòng chọn chức năng hợp lệ!");
                       }

                    }
                    break;
                case 4:
                   String exitConfirm = customUtil.getString("Bạn có chắc chắn muốn thoát chương trình không (Y/n): ");
                   if (exitConfirm.equalsIgnoreCase("y")) {
                       System.out.println("Cảm ơn bạn đã sử dụng hệ thống quản lý cửa hàng điện thoại!");
                       System.exit(0);
                   }
                   break;
                default:
                    System.out.println("Vui lòng chọn chức năng hợp lệ!");
            }
        }
    }
}